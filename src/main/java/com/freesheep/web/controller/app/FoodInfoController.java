package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.FoodInfoBO;
import com.freesheep.biz.service.FoodInfoService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/food")
public class FoodInfoController extends BaseSecretController {

    @Resource
    FoodInfoService foodInfoService;

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ResultView singleInfo(){
        Map<String, Object> map = testBody();
        if(map == null) return result(null, "参数错误");
        String infoIdStr = Utils.getSomeValue(map.get("info_id"));
        if(StringUtils.isBlank(infoIdStr)) return result(null, "参数错误");
        long infoId = Utils.parseLong(infoIdStr);
        if (infoId < 1) return result(null, "参数错误");
        FoodInfoBO foodInfoBO = foodInfoService.selectByPrimaryKey(infoId);
        if(foodInfoBO == null) return result(null, "没有该条记录");
        return result(foodInfoBO);
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultView list(){
        Map<String, Object> map = getBodyMap();
//        Map<String, Object> map = testBody();
        String pageStr ="";
        if(map!= null) {
            pageStr = Utils.getSomeValue(map.get("page"));
        }
        int page = 1;
        if(StringUtils.isNotBlank(pageStr)){
            page = Utils.parseInt(pageStr);
        }
        if(page < 1) page = 1;
        int pageSize = 10;

        Page<FoodInfoBO> appPage = foodInfoService.getFoodInfoList(getPageRequest(page, pageSize));
        List<FoodInfoBO> list = appPage.getContent();
        long total = appPage.getTotalElements();
        int totalPages = appPage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("list", list);
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);

        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
//        return result(json);
    }

}
