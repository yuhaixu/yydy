/*
 * Copyright (c)  2016-2017 Alibaba Group Holding Limited
 */

package com.freesheep.web.protral.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

/**
 * User yuexin.cyx
 * Date 2016-08-13
 * Time 12:52
 */
public class AliyunRequestMatcher implements RequestMatcher {
    private Logger logger = LoggerFactory.getLogger(AliyunRequestMatcher.class);
    /**
     * * 需要排除的url列表
     */
    private List<String> mappingPath;

    private List<String> excludeMappingPath;

    public List<String> getMappingPath() {
        return mappingPath;
    }

    public void setMappingPath(List<String> mappingPath) {
        this.mappingPath = mappingPath;
    }

    public List<String> getExcludeMappingPath() {
        return excludeMappingPath;
    }

    public void setExcludeMappingPath(List<String> excludeMappingPath) {
        this.excludeMappingPath = excludeMappingPath;
    }

    private final HashSet<String> allowedMethods = new HashSet<String>(
            Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.security.web.util.matcher.RequestMatcher#matches(javax.
     * servlet.http.HttpServletRequest)
     */
    @Override
    public boolean matches(HttpServletRequest request) {
        System.out.println("=============filter==============" + request.getRequestURI());
        System.out.println("请求方式" + request.getMethod());
        boolean match = this.allowedMethods.contains(request.getMethod());
        if (match) {
            return false;
        } else {
            if (excludeMappingPath != null && excludeMappingPath.size() > 0) {
                String servletPath = request.getServletPath();
                for (String regex : excludeMappingPath) {
                    Pattern p = Pattern.compile(regex);
                    if (p.matcher(servletPath).find()) {
                        return false;
                    }
                }
            }
            if (mappingPath != null && mappingPath.size() > 0) {
                String servletPath = request.getServletPath();
                for (String regex : mappingPath) {
                    Pattern p = Pattern.compile(regex);
                    if (p.matcher(servletPath).find()) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
