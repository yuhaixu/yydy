package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.ProductShareBO;
import com.freesheep.biz.service.ProductShareService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.DateUtils;
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
@RequestMapping("/app/pro_share")
public class ProductShareController extends BaseSecretController {

    @Resource
    ProductShareService shareService;

    @ResponseBody
    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public ResultView share(){
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String proIdStr = Utils.getSomeValue(map.get("pid"));

//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String proIdStr = Utils.getSomeValue(request.getParameter("pid"));

        if (StringUtils.isBlank(proIdStr) || StringUtils.isBlank(uidStr)) {
            return result(null, "参数错误");
        }

        long pid = Utils.parseLong(proIdStr);
        long uid = Utils.parseLong(uidStr);
        if (pid < 1 || uid < 1) return result(null, "参数错误");

        ProductShareBO shareBO = shareService.selectProductShare(uid, pid);
        Date date = new Date();
        JSONObject json = new JSONObject();
        json.put("time", date.getTime());
        if(shareBO == null){
            ProductShareBO insert = new ProductShareBO();
            insert.setProductId(pid);
            insert.setUserId(uid);
            insert.setIsShare(1);
            insert.setCreateTime(date);
            insert.setModifyTime(date);
            shareService.insertSelective(insert);
//            return result(json);
            return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
        } else {
            shareBO.setIsShare(1);
            shareBO.setModifyTime(date);
            shareService.updateByPrimaryKeySelective(shareBO);
            return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
//            return result(json);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/is_share", method = RequestMethod.POST)
    public ResultView isShare(){
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String proIdStr = Utils.getSomeValue(map.get("pid"));

//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String proIdStr = Utils.getSomeValue(request.getParameter("pid"));

        if (StringUtils.isBlank(proIdStr) || StringUtils.isBlank(uidStr)) {
            return result(null, "参数错误");
        }

        long pid = Utils.parseLong(proIdStr);
        long uid = Utils.parseLong(uidStr);
        if (pid < 1 || uid < 1) return result(null, "参数错误");

        ProductShareBO shareBO = shareService.selectProductShare(uid, pid);
        Date date = new Date();
        if(shareBO == null){
            JSONObject json = new JSONObject();
            json.put("is_share", 0);
            json.put("time", date.getTime());
            return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
        } else {
            JSONObject json = new JSONObject();
            json.put("is_share", shareBO.getIsShare());
            json.put("time", date.getTime());
            return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
        }
    }

}
