package com.tims.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 运行时工具类，用于获取一些运行中的参数信息
 *
 * @author chenyl 2017/9/21 10:37
 */
public class RuntimeUtil {

    private static Boolean isDevelopMode = null;

    /**
     * 开发模式
     */
    private static final String DEVELOP_MODE = "dev";

    /**
     * 获取当前的启动模式
     *
     * @return
     */
    private static String getActiveProfile(ApplicationContext applicationContext) {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }

    /**
     * 是否开发模式
     *
     * @param applicationContext
     * @return
     */
    public static boolean isDevelopMode(ApplicationContext applicationContext) {
        if (isDevelopMode != null) {
            return isDevelopMode.booleanValue();
        }
        String activeProfile = getActiveProfile(applicationContext);
        if (DEVELOP_MODE.indexOf(activeProfile) > -1) {
            isDevelopMode = true;
            return isDevelopMode;
        }
        isDevelopMode = false;
        return isDevelopMode;
    }

    /**
     * 只用于 代码单元测试时 调用
     */
    public static void setDevelopMode() {
        isDevelopMode = true;
    }

    /**
     * 获取环境参数
     *
     * @return
     */
    public static Environment getEnvironment() {
        return SpringContextUtil.getApplicationContext().getEnvironment();
    }

    /**
     * 获取环境参数值
     *
     * @param propertyKey
     * @return
     */
    public static String getEnvProperty(String propertyKey) {
        return getEnvironment().getProperty(propertyKey);
    }
}
