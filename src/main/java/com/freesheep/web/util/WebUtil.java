/*
 * Copyright (c)  2016-2017 Alibaba Group Holding Limited
 */

package com.freesheep.web.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User yuexin.cyx
 * Date 2016-07-04
 * Time 16:36
 */
public class WebUtil {
    public static void writeJson(Object obj, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            String out = JSONObject.toJSONString(obj);
            PrintWriter printWriter = response.getWriter();
            printWriter.write(out);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeJson(String obj, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(obj);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
