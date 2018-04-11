package com.tims.common.util;


import java.util.UUID;

public class PkUtil {

    /**
     * 获取32位唯一的uuid
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
