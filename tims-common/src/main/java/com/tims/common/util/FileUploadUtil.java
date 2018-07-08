package com.tims.common.util;

import java.io.IOException;

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

    public static String getRandomDirectory(String path) {

        int hashcode = path.hashCode();
        int a =hashcode & 0xf;
        return "/" + a%256;
    }

    public  static  void  main(String[]  args) throws IOException {
        System.out.println(getRandomDirectory("/temp/123"));
    }
}
