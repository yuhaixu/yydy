package com.freesheep.web.controller.web;

import com.freesheep.biz.model.StDeviceTypeBO;
import com.freesheep.biz.service.StDeviceTypeService;
import com.freesheep.web.controller.BaseController;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/web/device_type")
public class DeviceTypeController extends BaseController {

    @Resource
    StDeviceTypeService typeService;

    @ResponseBody
    @RequestMapping("/add")
    public ResultView addDeviceType(){

        String deviceName = Utils.getSomeValue(request.getParameter("device_name"));
        String remark = Utils.getSomeValue(request.getParameter("remark"));

        if(StringUtils.isEmpty(deviceName)) return result(null, "请求参数异常");

        StDeviceTypeBO typeBO = new StDeviceTypeBO();
        Date now = new Date();
        typeBO.setDeviceName(deviceName);
        if(!StringUtils.isEmpty(remark)){
            typeBO.setRemark(remark);
        }
        typeBO.setCreateTime(now);
        typeBO.setModifyTime(now);

        int row = typeService.insertSelective(typeBO);
        if(row > 0) {
            return result("添加成功");
        }
        return result(null, "添加失败");
    }

}
