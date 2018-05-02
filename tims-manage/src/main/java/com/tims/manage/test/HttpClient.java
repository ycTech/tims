package com.tims.manage.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpClient {
    public  static  final  String  CHARSET  =  "UTF-8";

     /*
     **
     * 通过拼接的方式构造请求内容，实现参数传输以及文件传输
     *
     * @param actionUrl 访问的服务器URL
     * @param params 普通参数
     * @param files 文件参数
     * @return
             * @throws IOException
     */
     public String uploadFile(String actionUrl, Map<String, String> params, Map<String, File> files) throws IOException {

        String BOUNDARY = UUID.randomUUID().toString();
        String PREFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";

        URL uri = new URL(actionUrl);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setReadTimeout(5 * 1000); // 缓存的最长时间
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false); // 不允许使用缓存
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

        // 首先组拼文本类型的参数
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet())
        {
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINEND);
            sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
            sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
            sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
            sb.append(LINEND);
            sb.append(entry.getValue());
            sb.append(LINEND);
        }

        DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
        outStream.write(sb.toString().getBytes());
        InputStream in = null;
        // 发送文件数据
        if (files != null)
        {
            for (Map.Entry<String, File> file : files.entrySet())
            {
                StringBuilder sb1 = new StringBuilder();
                sb1.append(PREFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINEND);
                // name是post中传参的键 filename是文件的名称
                sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getKey() + "\"" + LINEND);
                sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
                sb1.append(LINEND);
                outStream.write(sb1.toString().getBytes());

                InputStream is = new FileInputStream(file.getValue());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1)
                {
                    outStream.write(buffer, 0, len);
                }

                is.close();
                outStream.write(LINEND.getBytes());
            }

            // 请求结束标志
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            outStream.write(end_data);
            outStream.flush();
            // 得到响应码
            int res = conn.getResponseCode();
            if (res == 200)
            {
                System.out.println(conn.getResponseMessage());
                in = conn.getInputStream();
                int ch;
                StringBuilder sb2 = new StringBuilder();
                while ((ch = in.read()) != -1)
                {
                    sb2.append((char) ch);
                }
                return (sb2.toString());
            }
            outStream.close();
            conn.disconnect();
        }
        return null;
    }



    /** 
      * HTTP Post 获取内容
      * @return 页面内容 
      * @throws IOException  
      * @throws ClientProtocolException  
      */  
              
    public static String sendPost(String url, String params) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-type","application/json; charset=utf-8");  
        httpPost.setHeader("Accept", "application/json");

        StringEntity entity = new StringEntity(params, CHARSET);
        httpPost.setEntity(entity);     
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();  
        if (statusCode != 200) {  
            httpPost.abort();  
            throw new RuntimeException("HttpClient,error status code :" + statusCode);  
        }  
        HttpEntity responseEntity = response.getEntity();
        String result = null;  
        if (responseEntity != null) {
            result = EntityUtils.toString(responseEntity, "utf-8");
            EntityUtils.consume(entity);  
            response.close();  
            return result;  
        }else{  
             return null;  
        }  
    }

    /**
     * 从网络获取json数据,(String byte[})

     * @param path
     * @return
     */
    public static String getJsonByInternet(String path){
        try {
            URL url = new URL(path.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if(200 == urlConnection.getResponseCode()){
                //得到输入流
                InputStream is =urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while(-1 != (len = is.read(buffer))){
                    baos.write(buffer,0,len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //发送JSON字符串 如果成功则返回成功标识。
    public static String doJsonPost(String urlPath, String Json) {
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");
            // 往服务器里面发送数据
            if (Json != null) {
                byte[] writebytes = Json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
            }
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public  static  void  main(String[]  args) throws IOException {
        HttpClient httpsUtils  =  new HttpClient();
//        /**  1. POST请求   **/
//        String testUrl02 = "http://127.0.0.1:10060/smfile/save/user";
//        Map<String, Object> params02 = new HashMap<>();
//        params02.put("userCode", "001");
//        params02.put("userName", "测试用户");
//        String params="{\n" +
//                "  \"userCode\": \"002\",\n" +
//                "  \"userName\": \"test\"\n" +
//                "}";
//        System.out.println(sendPost(testUrl02,params));

        /**  2. GET请求   **/
//        String testUrl01 = "具体的测试接口地址";
//        Map<String, Object> params01 = new HashMap<>();
//        params01.put("参数01", "对应的参数取值");
//        params01.put("参数02", "");
//        System.out.println(getJsonByInternet(testUrl01, params01));

        /**  3. 文件上传 **/
        String  filePath  =  "c:/1/test5.doc";
        String  postUrl    =  "http://127.0.0.1:10060/smfile/upload";
        Map<String,String>  postParam  =  new  HashMap<String,String>();
        postParam.put("userCode",  "11122");
        postParam.put("billType",  "11122");
        postParam.put("billId",  "11122");
        postParam.put("billNo",  "11122");
        postParam.put("path",  "11122");
        postParam.put("isFolder",  "11122");

        File  postFile  =  new  File(filePath);
        Map<String, File> files=new HashMap<>();
        files.put("test5.doc",postFile);
        httpsUtils.uploadFile(postUrl,postParam,files);
    }

}