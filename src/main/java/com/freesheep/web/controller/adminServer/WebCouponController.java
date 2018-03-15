package com.freesheep.web.controller.adminServer;

import com.freesheep.biz.model.ProductCouponBO;
import com.freesheep.biz.service.ProductCouponService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.CouponUtils;
import com.freesheep.web.util.DateUtils;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/coupon")
public class WebCouponController extends BaseSecretController {

    @Resource
    ProductCouponService productCouponService;

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultView delete() {
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }

        String couponIdStr = Utils.getSomeValue(parMap.get("coupon_id"));
        if (StringUtils.isBlank(couponIdStr)) return result(null, "参数错误");
        long couponId = Utils.parseLong(couponIdStr);
        if(couponId < 1) return result(null, "参数错误");
        int low = productCouponService.deleteByPrimaryKey(couponId);
        if(low > 0) return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
        return result(null, "删除数据失败");
    }


    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultView add() {
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }

        String typeStr = Utils.getSomeValue(parMap.get("type"));
        String priceStr = Utils.getSomeValue(parMap.get("price"));
        String fullPriceStr = Utils.getSomeValue(parMap.get("full_price"));
        String productId = Utils.getSomeValue(parMap.get("product_id"));
        String overdueTime = Utils.getSomeValue(parMap.get("overdue_time"));
        String numberStr = Utils.getSomeValue(parMap.get("number"));

        if (StringUtils.isBlank(typeStr) || StringUtils.isBlank(numberStr) || StringUtils.isBlank(overdueTime)) {
            return result(null, "参数错误");
        }

        int type = Utils.parseInt(typeStr);
        int number = Utils.parseInt(numberStr);
        if (type < 1 || number < 1) {
            return result(null, "参数错误");
        }

        int pid = 0;
        double price = 0;
        double fullPrice = 0;
        if (type == 1) {
            if (StringUtils.isBlank(productId)) return result(null, "参数错误");
            pid = Utils.parseInt(productId);
            if (pid < 1) return result(null, "参数错误");
        } else if (type == 2) {
            if (StringUtils.isBlank(priceStr) || StringUtils.isBlank(fullPriceStr)) {
                return result(null, "参数错误");
            }
            price = Utils.parseDouble(priceStr);
            fullPrice = Utils.parseDouble(fullPriceStr);
            if (price <= 0 || fullPrice <= 0) return result(null, "参数错误");
        } else if (type == 3) {
            if (StringUtils.isBlank(priceStr)) {
                return result(null, "参数错误");
            }
            price = Utils.parseDouble(priceStr);
            if (price <= 0) return result(null, "参数错误");
        } else {
            return result(null, "没有此类标示");
        }

        Date oTime = DateUtils.fromTimer(overdueTime);
        if(oTime == null) return result(null, "时间格式有问题");
        Date date = new Date();

        List<ProductCouponBO> couponList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String couponCode = CouponUtils.getCoupon();
            ProductCouponBO info = productCouponService.getInfoByCode(couponCode);
            if (info != null){
                i--;
                continue;
            }
            ProductCouponBO productCouponBO = new ProductCouponBO();
            if (type == 1) {
                price = 0;
                fullPrice = 0;
            } else if (type == 3) {
                fullPrice = 0;
            }
            productCouponBO.setType(type);
            productCouponBO.setCouponCode(couponCode);
            productCouponBO.setIsUsed(0);
            productCouponBO.setPrice(price);
            productCouponBO.setFullPrice(fullPrice);
            productCouponBO.setProductId(pid);
            productCouponBO.setOverdueTime(oTime);
            productCouponBO.setCreateTime(date);
            productCouponBO.setModifyTime(date);
            couponList.add(productCouponBO);
        }

        boolean insert = productCouponService.insertCouponList(couponList);
        if(insert) return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));

        return result(null, "数据写入失败");
    }

}
