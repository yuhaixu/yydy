package com.freesheep.web.controller.device;

import com.freesheep.biz.model.DeviceTestInfoBO;
import com.freesheep.biz.service.DeviceTestInfoService;
import com.freesheep.web.controller.BaseController;
import com.freesheep.web.util.DateUtils;
import com.freesheep.web.util.Utils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

@Controller
public class DeviceEchoController extends BaseController {
    private Logger log = Logger.getLogger(DeviceEchoController.class);

    @Resource
    DeviceTestInfoService deviceTestInfoService;

    @RequestMapping(value = "/device/test", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void deviceTest() {
        Map<String, Object> map = getBodyMap();
        if(map == null){
            return;
        }

        String deviceNum = Utils.getSomeValue(map.get("device_num"));
        String deviceDate = Utils.getSomeValue(map.get("device_date"));
        String deviceTime = Utils.getSomeValue(map.get("device_time"));
        String longitude = Utils.getSomeValue(map.get("longitude"));
        String latitude = Utils.getSomeValue(map.get("latitude"));
        String isLocation = Utils.getSomeValue(map.get("is_location"));

        DeviceTestInfoBO deviceTestInfoBO = new DeviceTestInfoBO();
        if(StringUtils.isNotBlank(deviceNum)) deviceTestInfoBO.setDeviceNum(deviceNum);
        if(StringUtils.isNotBlank(deviceDate)) deviceTestInfoBO.setDeviceDate(deviceDate);
        if(StringUtils.isNotBlank(deviceTime)) deviceTestInfoBO.setDeviceTime(deviceTime);
        if(StringUtils.isNotBlank(longitude)) deviceTestInfoBO.setLongitude(longitude);
        if(StringUtils.isNotBlank(latitude)) deviceTestInfoBO.setLatitude(latitude);
        if(StringUtils.isNotBlank(isLocation)) deviceTestInfoBO.setIsLocation(isLocation);
        String date = DateUtils.getNowTime();
        deviceTestInfoBO.setCreateTime(date);
        deviceTestInfoBO.setModifyTime(date);

        deviceTestInfoService.insertSelective(deviceTestInfoBO);

    }


    @RequestMapping(value = "/device/sheep", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String newDevice() {
        String body = null;
        try {
            request.getInputStream();
            body = IOUtils.toString(request.getInputStream(), "UTF-8");
            System.out.println("post  " + body);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (!StringUtils.isNoneBlank(body)) {
            try {
                String uri = request.getQueryString();
                if(StringUtils.isNoneBlank(uri)) {
                    body = URLDecoder.decode(request.getQueryString(), "utf-8");
                    System.out.println("get  " + body);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (!StringUtils.isNoneBlank(body)){
            body = "null";
        }

        log.debug("==============device info start================");
        log.debug(DateUtils.getNowTime() + " : ");
        log.debug(body);
        log.debug("==============device info end================");

        return body;
    }


    @RequestMapping(value = "/device/echo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String echo() {


        String body = null;
        try {
            request.getInputStream();
            body = IOUtils.toString(request.getInputStream(), "UTF-8");
            System.out.println("post  " + body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isNoneBlank(body)) {
            try {
                String uri = request.getQueryString();
                if(StringUtils.isNoneBlank(uri)) {
                    body = URLDecoder.decode(request.getQueryString(), "utf-8");
                    System.out.println("get  " + body);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (!StringUtils.isNoneBlank(body)){
            body = "null";
        }

        return body;
    }

}
