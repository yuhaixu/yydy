package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.StOrdersBO;
import com.freesheep.biz.service.StOrdersService;
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
@RequestMapping("/app/order")
public class OrderController extends BaseSecretController {

    @Resource
    StOrdersService ordersService;


    @ResponseBody
    @RequestMapping(value = "/get_orders", method = RequestMethod.POST)
    public ResultView getOrders(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String pageStr = Utils.getSomeValue(map.get("page"));
        String typeStr = Utils.getSomeValue(map.get("type"));

//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String pageStr = Utils.getSomeValue(request.getParameter("page"));
        //0 未支付（或者叫待支付）1已支付（待发货）2已发货（待收货）3已收货（已完成）4退款取消（取消订单）
//        String typeStr = Utils.getSomeValue(request.getParameter("type"));


        if(StringUtils.isBlank(uidStr)) return result(null, "参数错误");
        long uid = Utils.parseLong(uidStr);
        if(uid < 1) return result(null, "参数错误");

        int page = 1;
        if(!StringUtils.isEmpty(pageStr)) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if(page < 1) page = 1;
        int pageSize = 25;
        int type = 0;
        if(StringUtils.isNotBlank(typeStr)) type = Utils.parseInt(typeStr);
        else type = -1;
        if(type > 4 || type < -1) return result(null, "参数错误");
        Page<StOrdersBO> appPage = ordersService.getOrderList(getPageRequest(page, pageSize), uid, type);
        List<StOrdersBO> list = appPage.getContent();
        long total = appPage.getTotalElements();
        int totalPages = appPage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("orders", list);
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
//        return result(json);
    }

}
