package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.ActivityImgBO;
import com.freesheep.biz.model.AppPageImgBO;
import com.freesheep.biz.model.ShowInfoBO;
import com.freesheep.biz.service.ActivityImgService;
import com.freesheep.biz.service.AppPageImgService;
import com.freesheep.biz.service.FictitiousSheepService;
import com.freesheep.biz.service.ShowInfoService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseController;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/hm")
public class HomePageController extends BaseSecretController {

    @Resource
    protected ActivityImgService imgService;
    @Resource
    protected AppPageImgService appPageImgService;
    @Resource
    protected ShowInfoService infoService;
    @Resource
    protected FictitiousSheepService sheepService;


    @ResponseBody
    @RequestMapping(value = "/home_info", method = RequestMethod.POST)
    public ResultView getHomePageInfo() {
        Map<String, Object> map = getBodyMap();
        String isClaimStr = null;
        String uidStr = null;
        if (map != null) {
            isClaimStr = Utils.getSomeValue(map.get("is_claim"));
            uidStr = Utils.getSomeValue(map.get("user_id"));
        }

        long uid = 0;
        int isClaim = 0;
        if(StringUtils.isNotBlank(uidStr)) {
            uid = Utils.parseLong(uidStr);
        }
        if(StringUtils.isNotBlank(isClaimStr)){
            isClaim = Utils.parseInt(isClaimStr);
        }


        JSONObject json = new JSONObject();
        List<ActivityImgBO> activityImgList = imgService.getAllInfo();
        if (activityImgList != null && activityImgList.size() > 0) {
            json.put("activity_img", activityImgList);
        }

        List<AppPageImgBO> appPageImgList = appPageImgService.getAllInfo();
        if (appPageImgList != null && appPageImgList.size() > 0) {
            json.put("page_img", appPageImgList);
        }

        ShowInfoBO showInfoBO = infoService.getShowInfo();
        json.put("show_info", showInfoBO);

        if(uid > 0) {
            long count = sheepService.getClaimCount(uid);
            json.put("is_claim", count > 0 ? "1" : "0");
        }

        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
//        return result(json);
    }

}
