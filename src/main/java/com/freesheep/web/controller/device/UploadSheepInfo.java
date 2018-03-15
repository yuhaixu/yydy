package com.freesheep.web.controller.device;

import com.freesheep.biz.model.StDeviceBO;
import com.freesheep.biz.model.StDeviceInfoBO;
import com.freesheep.biz.service.StDeviceInfoService;
import com.freesheep.biz.service.StDeviceService;
import com.freesheep.web.controller.BaseController;
import com.freesheep.web.util.DateUtils;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


@Controller
@RequestMapping("/device")
public class UploadSheepInfo extends BaseController {

    @Resource
    StDeviceService deviceService;
    @Resource
    StDeviceInfoService deviceInfoService;

    @ResponseBody
    @RequestMapping(value = "/up_sheep_info", method = RequestMethod.POST)
    public ResultView upSheepInfo(){
        String body = null;
        try {
            body = IOUtils.toString(request.getInputStream(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(StringUtils.isBlank(body)){
            return result(null,"error parameter, body is null");
        }
        Map<String, Object> map = getBodyMap();
        if(map == null){
            return result(null,"error parameter, please send right json");
        }
        System.out.println(map.toString());
        String data = Utils.getSomeValue(map.get("data"));
        if(StringUtils.isBlank(data)) return result(null,"error parameter, data is null");

        System.out.println("===================设备发送的信息===========================");
        System.out.println(data);

        // MC73,2,9912345,01700349374,241117081500,3957.27666,11620.58683,0000,000000,0

        String[] dataArr = data.split(",");
        if(dataArr.length != 10){
            System.out.println("信息错误");
        }
        String deviceNum = dataArr[2];
        StDeviceBO stDeviceBO = deviceService.selectByDeviceNum(deviceNum);
        StDeviceBO update = new StDeviceBO();
        Date date = new Date();
        Long deviceId = 0L;
        if(stDeviceBO == null){
            update.setDeviceNum(deviceNum);
            update.setDeviceTypeId(1);
            update.setDevicePower(Utils.parseInt(dataArr[9]));
            update.setPhone(dataArr[3]);
            update.setDeviceStatusId(1L);
            update.setCreateTime(date);
            update.setModifyTime(date);
            deviceId = deviceService.insertSelective(update);
        } else {
            stDeviceBO.setPhone(dataArr[3]);
            stDeviceBO.setDevicePower(Utils.parseInt(dataArr[9]));
            stDeviceBO.setModifyTime(date);
            deviceService.updateByPrimaryKeySelective(stDeviceBO);
            deviceId = stDeviceBO.getId();
        }
        if(deviceId > 0){
            StDeviceInfoBO deviceInfoBO = new StDeviceInfoBO();
            deviceInfoBO.setDeviceId(deviceId);
            deviceInfoBO.setDeviceLongitude(dataArr[6]);
            deviceInfoBO.setDeviceLatitude(dataArr[5]);
            deviceInfoBO.setDataTime(DateUtils.formatDeviceTime(dataArr[4]));
            deviceInfoBO.setStep(Utils.parseLong(dataArr[8]));
            deviceInfoBO.setCreateTime(date);
            deviceInfoBO.setModifyTime(date);
            int row = deviceInfoService.insertSelective(deviceInfoBO);
            if (row > 0){
                System.out.println("数据插入成功");
            }
        }

        return result(data);
    }


}
