package com.tims.common.util;


import java.io.IOException;
import java.security.MessageDigest;
import java.util.Random;

/***
 * Description : 密码加密、生成随机密码工具类
 * @author liuzm
 * @Description:
 * @date 2018/4/1817:11
 */

public class PwdEncryptUtil
{
    /**
     * MD5加密
     * @param 	orgStr		MD5加密前的明文
     * @return	String		MD5加密后的密文
     */
    private static String encryptMD5(String orgStr)
    {
        String retStr = "";
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] mdtemp = md.digest(orgStr.getBytes());
            for (int i = 0; i < mdtemp.length; i++)
            {
                retStr += Integer.toHexString(mdtemp[i] + 512).substring(1);
            }
        }
        catch(Exception e)
        {
            System.out.println("MD5加密失败！");
            e.printStackTrace();
        }
        return retStr;
    }

    /**
     * SHA加密
     * @param 	orgStr		SHA加密前的明文
     * @return	String		SHA加密后的密文
     */
    private static String encryptSHA(String orgStr)
    {
        String retStr = "";
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] mdtemp = md.digest(orgStr.getBytes());
            for (int i = 0; i < mdtemp.length; i++)
            {
                retStr += Integer.toHexString(mdtemp[i] + 512).substring(1);
            }
        }
        catch(Exception e)
        {
            System.out.println("SHA加密失败！");
            e.printStackTrace();
        }
        return retStr;
    }

    /**
     * 加密
     * @param 	str		要加密的字符串
     * @return	String	加密后的字符串
     */
    public static String encrypt(String str)
    {
        if(str == null || str.trim().length() == 0)
            return "";
        return encryptSHA(encryptMD5(str) + "SHRY");
    }

    /**
     * 获取随机的六位密码
     * @return	String	随机六位密码
     */
    public static String getRandomPwd()
    {
        String retStr = "";
        for(int i = 0; i < 6; i++)
        {
            retStr += new Random().nextInt(10);
        }
        return retStr;
    }

    /**
     * 编码
     * @param bstr
     * @return String
     */
    public static String encodeBase64(byte[] bstr){
        return new sun.misc.BASE64Encoder().encode(bstr);
    }

    /**
     * 解码
     * @param str
     * @return String
     */
    public static byte[] decodeBase64(String str) {
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bt;
    }

}

