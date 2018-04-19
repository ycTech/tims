/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.tims.manage.config;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Carl
 * @date 2017/9/28
 * @since 1.5.0
 */
@Component
public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private List<String> urlList;

    public SimpleUrlPatternMatcherStrategy () {
    }

    @PostConstruct
    public void init() {
        urlList = new ArrayList<>();
        urlList.add(".*mobile-page-end\\.html");
        urlList.add(".*mobile-page-live\\.html");
        urlList.add(".*pc-page-live\\.html");
        urlList.add(".*pc-page-end\\.html");
    }

    @Override
    public boolean matches(String url) {
        return false;
    }

    private Boolean matcheUrl(String url) {
        logger.info("请求URL:{}",url);
        for (String s : urlList) {
            if (Pattern.matches(s, url)) {
                logger.info("match {}",s);
                return true;
            }
        }
        return false;
    }

    @Override
    public void setPattern(String pattern) {

    }
}
