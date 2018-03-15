package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.IosUpdateBO;
import com.freesheep.biz.service.IosUpdateService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.vo.ResultView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class IOSUpdateController extends BaseSecretController {

    @Resource
    IosUpdateService service;

    @RequestMapping("/app/ios_update")
    @ResponseBody
    public ResultView IosUpdate(){
        IosUpdateBO bo = service.selectByPrimaryKey(1L);
        JSONObject json = new JSONObject();
        json.put("version", bo.getVersion());
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }


}
