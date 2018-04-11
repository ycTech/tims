package com.tims.common.util;

import java.util.UUID;

public class PkUtil {

    public PkUtil() {
    }
    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}