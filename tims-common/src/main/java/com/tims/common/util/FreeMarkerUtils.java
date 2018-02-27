package com.tims.common.util;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;

/**
 * FreeMarker的工具类
 *
 * @author liuzm 2017/8/31 17:55
 */
public class FreeMarkerUtils {

    private static final Logger log = Logger.getLogger(FreeMarkerUtils.class);

    private static Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);

    public static void setTemplateLoader(TemplateLoader templateLoader) {
        cfg.setTemplateLoader(templateLoader);
    }

    public static Template getTemplate(String name) {
        try {
            return cfg.getTemplate(name, "utf-8");
        } catch (Exception e) {
            log.error("加载FreeMarker模板失败，模板名称：" + name);
            log.error(e.getMessage(), e);
            throw new RuntimeException("加载FreeMarker模板失败，模板名称：" + name, e);
        }
    }
}
