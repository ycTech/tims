package com.tims.common.util;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 判断文件类型是否符合
 * @author: liuzm
 * @create: 2017/12/19  17:46
 */
public class FileUploadUtil {

    /**
     * 判断是否为允许的上传文件类型,true表示允许
     */
    public static boolean checkFile(String fileName) {
        //设置允许上传文件类型
        String suffixList = "jpg,png,pdf";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")
            + 1, fileName.length());
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }

    //获取加密文件名称
    public static String getEncryFileName(String filename) throws Exception {
        int index= filename.lastIndexOf(".");
        if (index!= -1) {
            return encodeFileName(filename);
        } else {
            return filename;
        }
    }

    //根据获取文件名称（YXB8/1003G9100000001E6QP0/发票(WEB)/SCAN_2016-06-23 83514.pdf）
    public static String getFileName(String path) throws Exception {
        return path.substring(path.lastIndexOf("/")+1, path.length());
    }

    public static String getFileNameByPath(String path) throws Exception {
        return path.replaceAll("/","");
    }

    public static String getBillTypeByPath(String path) throws Exception {
        return path.substring(0, path.indexOf("/"));
    }

    /**
     * 加密图片路径
     * @param fileName
     * @return
     */
    public static String encodeFileName(String fileName) throws Exception{
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        String postfix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String encodeName = new sun.misc.BASE64Encoder().encode(name.getBytes("UTF-8"));
        return encodeName + postfix;
    }

    /**
     * 解密图片路径, 返回图片附件名称
     * @return
     */
    public static String decodeFileName(String filePath) throws Exception {
        if(StringUtils.isEmpty(filePath)){
            return null;
        }
        String name = filePath.substring(0, filePath.lastIndexOf("."));
        String postfix = filePath.substring(filePath.lastIndexOf("."), filePath.length());
        String decodeName = new String(new sun.misc.BASE64Decoder().decodeBuffer(name), "UTF-8");
        return decodeName + postfix;
    }


    public static String getRandomDirectory(String path) {

        int hashcode = path.hashCode();
        int a =hashcode & 0xf;
        hashcode = hashcode >>> 4;
        int b =hashcode & 0xf;
        return "/" + a + "/" + b;
    }

    public  static  void  main(String[]  args) throws Exception {
        System.out.println(getRandomDirectory("/temp/123"));
        String encodeFile=encodeFileName("test.txt");
        System.out.println(encodeFile);
        System.out.println(decodeFileName(encodeFile));
    }
}
