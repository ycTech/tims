package com.tims.manage.test;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileClient {
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

    public  static  void  main(String[]  args)  {
        FileClient  httpsUtils  =  new  FileClient();
        //要上传的文件的路径
        String  filePath  =  "c:/1/test5.doc";
        String  postUrl    =  "http://hy.nfox.ml:10060/smfile/upload";
        Map<String,String>  postParam  =  new  HashMap<String,String>();
        postParam.put("userCode",  "11122");
        postParam.put("billType",  "11122");
        postParam.put("billId",  "11122");
        postParam.put("billNo",  "11122");
        postParam.put("path",  "11122");
        postParam.put("isFolder",  "11122");

        File  postFile  =  new  File(filePath);
        Map<String,Object>  resultMap  =  httpsUtils.uploadFileByHTTP(postFile,postUrl,postParam);
        System.out.println(resultMap);
    }

}