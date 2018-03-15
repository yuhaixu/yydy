<%@ page import="java.util.Map" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.freesheep.web.net.HttpRequest" %>
<%@ page import="java.lang.reflect.Type" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>美食记</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport"content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0,minimum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="renderer" content="webkit">
    <style>
        div
        {
            width: 100%;
        }

        img
        {
            width: 80%;
            height: auto;
        }

        p
        {
            text-align: center;
        }
    </style>
</head>
<body>

<%

    String id = request.getParameter("id");
    JSONObject json = new JSONObject();
    json.put("info_id", id);
    String url = "http://60.205.220.219:8080/app/food/info";
    String req = HttpRequest.jsonPost(url, json.toJSONString());
    Type t = new TypeToken<Map<String, Object>>() {
    }.getType();
    Map<String, Object> reqMap = new Gson().fromJson(req, t);
    String result = (String) reqMap.get("result");
    if(!"1".equals(result)) {
        String message = (String) reqMap.get("message");
        out.println(message);
        return;
    }

    Map<String, Object> dataMap = (Map<String, Object>) reqMap.get("data");
    String content = (String) dataMap.get("content");
    if(StringUtils.isBlank(content)) return;
    int startPosition = content.indexOf("[%");
    int endPosition = content.indexOf("%]");

    while (startPosition >= 0){
        out.println(content.substring(0, startPosition));
%>
<br/>
<%
        String imgUrl = content.substring(startPosition + 2, endPosition);
%>
<div>
    <P>
<img src="<%=imgUrl%>" />
    </P>
</div>
<%
        content = content.substring(endPosition + 2);
        startPosition = content.indexOf("[%");
        endPosition = content.indexOf("%]");
        if(startPosition < 0){
            out.println(content);
        }
%>
<br/>
<%
    }
%>


</body>
</html>
