package com.freesheep.web.controller.app;

import com.freesheep.biz.model.StMomentsBO;
import com.freesheep.biz.service.StMomentsService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Controller
public class ShareController extends BaseSecretController {

    @Resource
    StMomentsService service;

    @RequestMapping(value = "/app/share_circle", method = RequestMethod.POST)
    @ResponseBody
    public ResultView shareCircle(){
        Map<String, Object> map = getBodyMap();
        if(map == null){
            return result(null,"请求参数异常");
        }
        String mid = Utils.getSomeValue(map.get("mid"));
        if(StringUtils.isEmpty(mid)){
            return result(null,"请求参数异常");
        }

        int id = 0;
        try {
            id = Integer.parseInt(mid);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return result(null,"请求参数异常");
        }

        StMomentsBO momentsBO = service.selectByPrimaryKey(id);
        if(momentsBO == null){
            ResultView resultView = result(null, "羊友圈信息不存在");
            resultView.setResult("2");
            return resultView;
        }

        momentsBO.setModified(new Date());
        long count = momentsBO.getShareCount();
        momentsBO.setShareCount(count+1);
        boolean isOk = service.updateShareCount(momentsBO);

        if(isOk){
            return result(AESUtil.encryptForBase64("分享成功", Constant.APP_REQUEST));
        }
        return result(null,"分享失败");
    }

}
