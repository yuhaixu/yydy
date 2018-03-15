package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.ProductCommentBO;
import com.freesheep.biz.model.ProductCouponBO;
import com.freesheep.biz.service.ProductCouponService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/coupon")
public class CouponController extends BaseSecretController {

    @Resource
    ProductCouponService couponService;

    @ResponseBody
    @RequestMapping(value = "/exchange", method = RequestMethod.POST)
    public ResultView exchange(){
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap();
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        String userId = Utils.getSomeValue(parMap.get("user_id"));
        String couponCode = Utils.getSomeValue(parMap.get("coupon_code"));

        if(StringUtils.isBlank(userId) || StringUtils.isBlank(couponCode) ) {
            return result(null, "参数错误");
        }
        long uid = Utils.parseLong(userId);
        if(uid < 1) return result(null, "参数错误");
        ProductCouponBO couponBO =couponService.getInfoByCode(couponCode);
        if(couponBO == null) return result(null, "优惠券信息不存在或已过期");
        long cuid = couponBO.getUserId();
        if (cuid > 0) return result(null, "优惠券信息不存在或已过期");

        couponBO.setUserId(uid);
        Date date = new Date();
        couponBO.setModifyTime(date);
        int row = couponService.updateByPrimaryKeySelective(couponBO);
        if(row > 0){
            return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.APP_REQUEST));
        } else {
            return result(null, "兑换失败");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/use", method = RequestMethod.POST)
    public ResultView useCoupon(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String cidStr = Utils.getSomeValue(map.get("coupon_id"));

//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String cidStr = Utils.getSomeValue(request.getParameter("coupon_id"));

        if(StringUtils.isBlank(uidStr) || StringUtils.isBlank(cidStr) ) {
            return result(null, "参数错误");
        }
        long uid = Utils.parseLong(uidStr);
        long cid = Utils.parseLong(cidStr);
        if(uid < 1 || cid < 1) return result(null, "参数错误");

        ProductCouponBO couponBO = new ProductCouponBO();
        couponBO.setId(cid);
        couponBO.setIsUsed(1);
        Date date = new Date();
        couponBO.setModifyTime(date);
        int row = couponService.updateByPrimaryKeySelective(couponBO);
        if(row > 0){
            return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.APP_REQUEST));
        } else {
            return result(null, "修改失败");
        }

    }


    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultView getCouponList(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String pageStr = Utils.getSomeValue(map.get("page"));

//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String pageStr = Utils.getSomeValue(request.getParameter("page"));

        if(StringUtils.isBlank(uidStr)) {
            return result(null, "参数错误");
        }

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
        int pageSize = 10;

        Page<ProductCouponBO> pasturePage = couponService.getCouponList(getPageRequest(page, pageSize), uid);
        List<ProductCouponBO> list = pasturePage.getContent();
        long total = pasturePage.getTotalElements();
        int totalPages = pasturePage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);
        json.put("couponList", list);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
//        return result(json);
    }


}
