package com.freesheep.web.util;

import com.freesheep.biz.jiuBean.ItemBean;
import com.freesheep.biz.jiuBean.ResponseBean;
import com.freesheep.common.util.DigestUtils;
import com.freesheep.web.config.jiuConfig.JiuConfig;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class JiuUtils {


    public static ResponseBean getBean(String flag, String code, String msg) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setFlag(flag);
        responseBean.setCode(code);
        if (msg != null) {
            responseBean.setMessage(msg);
        }
        return responseBean;
    }


    public static String getResponseXml(ResponseBean response) {
        XStream resStre = new XStream(new DomDriver("utf-8"));
        resStre.alias("response", ResponseBean.class);
        String xml = resStre.toXML(response);
        return xml;
    }

    public static ResponseBean getResponse(String res){
        ResponseBean responseBean = null;
        if (StringUtils.isNotBlank(res)) {
            XStream resX = JiuUtils.createXstream();
            resX.alias("response", ResponseBean.class);
            resX.alias("item", ItemBean.class);
            resX.processAnnotations(ResponseBean.class);
            responseBean = (ResponseBean) resX.fromXML(res);
            System.out.println(responseBean.toString());
        }
        return responseBean;
    }

    public static String getRequestUrl(String method , String body) {
        Map<String, String> map = JiuUtils.getPar(method);

        Set<String> keySet = map.keySet();
        Iterator<String> iterable = keySet.iterator();
        List<String> keyList = new ArrayList<>();
        while (iterable.hasNext()) {
            String key = iterable.next();
            keyList.add(key);
        }

        keyList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        StringBuilder urlPar = new StringBuilder();
        StringBuilder secretPar = new StringBuilder();
        secretPar.append(JiuConfig.JIU_APP_SECRET);
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            urlPar.append(key);
            urlPar.append("=");
            urlPar.append(map.get(key));
            urlPar.append("&");
            secretPar.append(key);
            secretPar.append(map.get(key));
        }
        String urlParStr = urlPar.toString().substring(0, urlPar.length() - 1);
        System.out.println("urlParStr = " + urlParStr);
        secretPar.append(body);
        secretPar.append(JiuConfig.JIU_APP_SECRET);
        System.out.println("bodyInfo = " + secretPar.toString());

        String jiuResp = "";
        String secret = "";
        try {
            secret = DigestUtils.encodeMD5Hex(secretPar.toString().getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        secret = secret.toUpperCase();
        System.out.println("secret = " + secret);
        urlParStr = urlParStr + "&sign=" + secret;
        System.out.println("urlParStr = " + urlParStr);
        String sendUrlPar = urlParStr.replace(" ", "%20");
        System.out.println("sendBody = " + sendUrlPar);
        String url = JiuConfig.JIU_REQUEST_URL + sendUrlPar;
        System.out.println("send url = " + url);
        return url;
    }

    public static Map<String, String> getPar(String requestMethod) {
        Map<String, String> map = new HashMap<>();
        map.put("method", requestMethod);
        map.put("timestamp", DateUtils.getNowTime());
        map.put("format", "xml");
        map.put("app_key", JiuConfig.JIU_APP_KEY);
        map.put("v", "2.0");
        map.put("sign_method", "md5");
        map.put("customerId", JiuConfig.CUSTOMER_ID);
        return map;
    }

    /**
     * 创建XStream
     * 不需要一一对应也可以创建对象成功不会报错
     */
    public static XStream createXstream() {
        XStream xstream = new XStream(new MyXppDriver(false)) {
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        xstream.autodetectAnnotations(true);
        return xstream;
    }

}
