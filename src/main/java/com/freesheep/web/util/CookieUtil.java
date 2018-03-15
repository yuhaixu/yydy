/*
 * Copyright (c)  2016-2017 Alibaba Group Holding Limited
 */

package com.freesheep.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User yuexin.cyx
 * Date 2016-08-10
 * Time 16:57
 */
public class CookieUtil {
    /**
     * 添加cookie
     *
     * @param name   cookie的key
     * @param value  cookie的value
     * @param domain domain
     *               ＠param  path path
     * @param maxAge 最长存活时间 单位为秒
     */
    public static void addCookie(String name, String value, String domain,
                                 int maxAge, String path, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 往根下面存一个cookie
     * * @param name cookie的key
     *
     * @param value    cookie的value
     * @param domain   domain
     * @param maxAge   最长存活时间 单位为秒
     * @param response response
     */
    public static void addCookie(String name, String value, String domain,
                                 int maxAge, HttpServletResponse response) {
        addCookie(name, value, domain, maxAge, "/", response);
    }

    /**
     * 从cookie值返回cookie值，如果没有返回 null
     *
     * @param request request
     * @param name    cookie名称
     * @return cookie的值
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void removeCookie(String name, String domain, HttpServletRequest request, HttpServletResponse response) {
        String cookieVal = getCookie(request, name);
        if (cookieVal != null) {
            CookieUtil.addCookie(name, null, domain, 0, response);
        }
    }

}
