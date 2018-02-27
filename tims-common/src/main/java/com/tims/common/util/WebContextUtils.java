package com.tims.common.util;

import org.apache.log4j.Logger;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * web上下文工具类
 *
 * @author liuzm
 * @create 2018-02-27 17:46
 **/
public class WebContextUtils {

    private static final Logger log = Logger.getLogger(WebContextUtils.class);
    /**
     * 用户对象标示
     */
    public static final String USER = "USER_OBJ";

//    /**
//     * @param request 设定文件
//     * @return Account 返回类型
//     * @throws
//     * @Title: genUser
//     * @Description: 返回用户信息对象
//     */
//    public static SysUser getUser(HttpServletRequest request) {
//        Assert.notNull(request, "[request]不能为空！");
//        Object obj = request.getSession().getAttribute(USER);
//        if (obj instanceof SysUser) {
//            return (SysUser) obj;
//        } else {
//            return null;
//        }
//    }
//
//    public static SysUser getUser(HttpSession session) {
//        Assert.notNull(session, "[session]不能为空！");
//        Object obj = session.getAttribute(USER);
//        if (obj instanceof SysUser) {
//            return (SysUser) obj;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * @return UserAccount 返回类型
//     * @throws
//     * @Title: genUser
//     * @Description: 返回用户信息对象 设定文件
//     */
//    public static SysUser getCurrentUser() {
//        Assert.notNull(getRequest(), "http request 不存在");
//        Object obj = getRequest().getSession().getAttribute(USER);
//        return getCurrentUser(obj);
//    }
//
//
//    /**
//     * 转化当前用户
//     *
//     * @param obj
//     * @return
//     */
//    private static SysUser getCurrentUser(Object obj) {
//        if (obj instanceof SysUser) {
//            return (SysUser) obj;
//        }
//        return null;
//
//    }

    /**
     * 获取request信息
     *
     * @return
     */
    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取response信息
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }


    public static EmbeddedServletContainerCustomizer getEmbeddedServletContainerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/400");
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
                container.addErrorPages(error400Page, error401Page, error404Page, error500Page);
            }
        };
    }
}
