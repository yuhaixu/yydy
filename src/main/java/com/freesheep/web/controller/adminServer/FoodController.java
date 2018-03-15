package com.freesheep.web.controller.adminServer;

import com.freesheep.biz.model.FoodInfoBO;
import com.freesheep.biz.service.FoodInfoService;
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
@RequestMapping("/admin/food")
public class FoodController extends BaseSecretController {

    @Resource
    FoodInfoService foodInfoService;

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultView delete(){
        Map<String, Object> parMap = null;
        try {
//            parMap = getBodyMap(Constant.ADMIN_REQUEST);
            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if (parMap == null) return result(null, "参数错误");

        String infoIdStr = Utils.getSomeValue(parMap.get("info_id"));
        if(StringUtils.isBlank(infoIdStr)) return result(null, "参数错误");
        long infoId = Utils.parseLong(infoIdStr);
        if (infoId < 1) return result(null, "参数错误");

        FoodInfoBO foodInfoBO = foodInfoService.selectByPrimaryKey(infoId);
        if(foodInfoBO == null) return result(null, "没有该条记录");

        int low = foodInfoService.deleteByPrimaryKey(infoId);

        if(low < 1) return result(null, "数据删除失败");
        return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
    }

    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResultView modify(){
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if (parMap == null) return result(null, "参数错误");
        String infoIdStr = Utils.getSomeValue(parMap.get("info_id"));
        String uidStr = Utils.getSomeValue(parMap.get("uid"));
        String firstImg = Utils.getSomeValue(parMap.get("first_img"));
        String title = Utils.getSomeValue(parMap.get("title"));
        String description = Utils.getSomeValue(parMap.get("description"));
        String content = Utils.getSomeValue(parMap.get("content"));
        String isShow = Utils.getSomeValue(parMap.get("is_show"));

        if(StringUtils.isBlank(infoIdStr)) return result(null, "参数错误");

        long infoId = Utils.parseLong(infoIdStr);
        if (infoId < 1) return result(null, "参数错误");
        FoodInfoBO foodInfoBO = foodInfoService.selectByPrimaryKey(infoId);
        if(foodInfoBO == null) return result(null, "没有该条记录");

        if(StringUtils.isNotBlank(uidStr)){
            long uid = Utils.parseLong(uidStr);
            if(uid > 0) foodInfoBO.setUserId(uid);
        }
        if(StringUtils.isNotBlank(firstImg)) foodInfoBO.setFirstImg(firstImg);
        if(StringUtils.isNotBlank(title)) foodInfoBO.setTitle(title);
        if(StringUtils.isNotBlank(description)) foodInfoBO.setDescription(description);
        if(StringUtils.isNotBlank(content)){
            content = content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
            foodInfoBO.setContent(content);
        }
        if(StringUtils.isNotBlank(isShow)){
            int showFlag = Utils.parseInt(isShow);
            if(showFlag < 0 || showFlag > 1) showFlag = 0;
            foodInfoBO.setIsShow(showFlag);
        }
        Date date = new Date();
        foodInfoBO.setModifyTime(date);
        int low = foodInfoService.updateByPrimaryKeySelective(foodInfoBO);
        if(low < 1) return result(null, "数据修改失败");
        return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultView add(){
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if (parMap == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(parMap.get("uid"));
        String firstImg = Utils.getSomeValue(parMap.get("first_img"));
        String title = Utils.getSomeValue(parMap.get("title"));
        String description = Utils.getSomeValue(parMap.get("description"));
        String content = Utils.getSomeValue(parMap.get("content"));
        String isShow = Utils.getSomeValue(parMap.get("is_show"));
        if(StringUtils.isBlank(firstImg) || StringUtils.isBlank(title) || StringUtils.isBlank(description) ||
                StringUtils.isBlank(content) ){
            return result(null, "参数错误");
        }
        content = content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");

        Date date = new Date();
        int showFlag = 0;
        if(StringUtils.isNotBlank(isShow)) {
            showFlag = Integer.parseInt(isShow);
        }
        if(showFlag < 0 || showFlag > 1) showFlag = 0;
        FoodInfoBO foodInfoBO = new FoodInfoBO();
        if(StringUtils.isNotBlank(uidStr)) foodInfoBO.setUserId(Utils.parseLong(uidStr));
        else foodInfoBO.setUserId(133L);
        foodInfoBO.setFirstImg(firstImg);
        foodInfoBO.setTitle(title);
        foodInfoBO.setDescription(description);
        foodInfoBO.setContent(content);
        foodInfoBO.setIsShow(showFlag);
        foodInfoBO.setCreateTime(date);
        foodInfoBO.setModifyTime(date);
        int low = foodInfoService.insertSelective(foodInfoBO);
        if(low < 1) return result(null, "数据写入失败");
        return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
    }

}
