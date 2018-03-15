package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.StProductsBO;
import com.freesheep.biz.service.StProductsService;
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
import java.util.Map;

@Controller
@RequestMapping("/app/product")
public class GetProductInfoController extends BaseSecretController {

    @Resource
    StProductsService productsService;

    @ResponseBody
    @RequestMapping(value = "/num", method = RequestMethod.POST)
    public ResultView getNumber() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");
        String pidStr = Utils.getSomeValue(map.get("pid"));
//        String pidStr = Utils.getSomeValue(request.getParameter("pid"));

        if (StringUtils.isBlank(pidStr)) {
            return result(null, "参数错误");
        }

        int pid = Utils.parseInt(pidStr);
        if (pid <= 0) return result(null, "参数错误");

        StProductsBO productsBO = productsService.selectByPrimaryKey(pid);
        if (productsBO != null) {
            int num = productsBO.getNumber();
            JSONObject json = new JSONObject();
            json.put("num", num);
            return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
        }

        return result(null, "参数错误");
    }

    @ResponseBody
    @RequestMapping(value = "/nums", method = RequestMethod.POST)
    public ResultView getNumbers() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");
        String pidStr = Utils.getSomeValue(map.get("pids"));

//        String pidStr = Utils.getSomeValue(request.getParameter("pids"));

        if (StringUtils.isBlank(pidStr)) {
            return result(null, "参数错误");
        }

        String[] arr = pidStr.split(",");
        if (arr == null || arr.length <= 0) return result(null, "参数错误");

        JSONObject json = new JSONObject();
        for (int i = 0; i < arr.length; i++) {
            int pid = Utils.parseInt(arr[i]);
            StProductsBO productsBO = productsService.selectByPrimaryKey(pid);
            if (productsBO != null) {
                json.put(String.valueOf(pid), productsBO.getNumber());
            }
        }
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
//        return result(json);
    }

}
