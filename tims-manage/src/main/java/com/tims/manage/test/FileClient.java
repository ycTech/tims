package com.tims.manage.test;

import com.tims.common.exception.BusinessException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

import static com.sun.xml.internal.ws.api.message.Packet.State.ClientRequest;

public class FileClient {
    public  static  final  String  CHARSET  =  "UTF-8";    
    /** 
          * 模拟表单上传文件 
          */  
    public Map<String,Object> uploadFileByHTTP(File postFile, String postUrl, Map<String,String> postParam){  
         Map<String,Object> resultMap = new HashMap<String,Object>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
         try{    
             //把一个普通参数和文件上传给下面这个地址
             HttpPost httpPost = new HttpPost(postUrl);    
             //把文件转换成流对象FileBody  
             FileBody fundFileBin = new FileBody(postFile);
             //设置传输参数  
             MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
             multipartEntity.addPart("file", fundFileBin);//相当于<input type="file" name="media"/>
             //设计文件以外的参数  
             Set<String> keySet = postParam.keySet();
             for (String key : keySet) {  
                //相当于<input type="text" name="name" value=name>    
                multipartEntity.addPart(key, new StringBody(postParam.get(key), ContentType.create("text/plain", Consts.UTF_8)));
             }  
               
             HttpEntity reqEntity =  multipartEntity.build();   
             httpPost.setEntity(reqEntity);    
                 
            //发起请求   并返回请求的响应
             CloseableHttpResponse response = httpClient.execute(httpPost);    
             try {    
                 //打印响应状态
                 resultMap.put("statusCode", response.getStatusLine().getStatusCode());
                 //获取响应对象    
                 HttpEntity resEntity = response.getEntity();    
                 if (resEntity != null) {    
                     //打印响应长度    
                     //打印响应内容
                     resultMap.put("data", EntityUtils.toString(resEntity,Charset.forName("UTF-8")));
                 }    
                 //销毁    
                 EntityUtils.consume(resEntity);    
             } catch (Exception e) {  
                 e.printStackTrace();  
             } finally {    
                 response.close();    
             }    
         } catch (ClientProtocolException e1) {
            e1.printStackTrace();  
         } catch (IOException e1) {  
            e1.printStackTrace();  
         } finally{    
             try {  
                httpClient.close();  
             } catch (IOException e) {  
                e.printStackTrace();  
             }    
         }
        return resultMap;    
     }
     
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
     public static void uploadFile(String actionUrl, Map<String, String> params, Map<String, File> files) throws IOException {

        String BOUNDARY = java.util.UUID.randomUUID().toString();
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
                System.out.println(sb2.toString());
            }
            outStream.close();
            conn.disconnect();
        }
        // return in.toString();
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
      * HTTP Get 获取内容
      * @return 页面内容 
      */
    public static String sendGet(String url, Map<String, Object> params) throws ParseException, UnsupportedEncodingException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        if(params !=null && !params.isEmpty()){  
              
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());  
              
            for (String key :params.keySet()){  
                pairs.add(new BasicNameValuePair(key, params.get(key).toString()));  
            }  
            url +="?"+EntityUtils.toString(new UrlEncodedFormEntity(pairs), CHARSET);  
        }  
          
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();  
        if(statusCode !=200){  
            httpGet.abort();  
            throw new RuntimeException("HttpClient,error status code :" + statusCode);  
        }  
        HttpEntity entity = response.getEntity();  
        String result = null;  
        if (entity != null) {  
            result = EntityUtils.toString(entity, "utf-8");  
            EntityUtils.consume(entity);  
            response.close();  
            return result;  
        }else{  
            return null;  
        }  
    }  

    public  static  void  main(String[]  args) throws IOException {
        FileClient  httpsUtils  =  new  FileClient();
//        //1.要上传的文件的路径
//        String  filePath  =  "c:/1/test5.doc";
//        String  postUrl    =  "http://hy.nfox.ml:10060/smfile/upload";
//        Map<String,String>  postParam  =  new  HashMap<String,String>();
//        postParam.put("userCode",  "11122");
//        postParam.put("billType",  "11122");
//        postParam.put("billId",  "11122");
//        postParam.put("billNo",  "11122");
//        postParam.put("path",  "11122");
//        postParam.put("isFolder",  "11122");
//
//        File  postFile  =  new  File(filePath);
//        Map<String,Object>  resultMap  =  httpsUtils.uploadFileByHTTP(postFile,postUrl,postParam);
//        System.out.println(resultMap);
        /**  2. POST请求   **/
//        String testUrl02 = "http://hy.nfox.ml:10060/smfile/save/user";
//        Map<String, Object> params02 = new HashMap<>();
//        params02.put("userCode", "001");
//        params02.put("userName", "测试用户");
//        String params="{\n" +
//                "  \"userCode\": \"002\",\n" +
//                "  \"userName\": \"test\"\n" +
//                "}";
//        System.out.println(sendPost(testUrl02,params));
        /**  3. GET请求   **/
//        String testUrl01 = "具体的测试接口地址";
//        Map<String, Object> params01 = new HashMap<>();
//        params01.put("参数01", "对应的参数取值");
//        params01.put("参数02", "");
//        System.out.println(sendGet(testUrl01, params01));

        /**  4. 文件上传0请求2   **/
        String  filePath  =  "c:/1/test5.doc";
        String  postUrl    =  "http://116.85.15.59:10060/smfile/upload";
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
        uploadFile(postUrl,postParam,files);
    }

}