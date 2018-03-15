package com.freesheep.web.controller.app;


import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.StClaimOrderBO;
import com.freesheep.biz.model.StSheepBO;
import com.freesheep.biz.service.StClaimOrderService;
import com.freesheep.biz.service.StSheepService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.manager.WorkManager;
import com.freesheep.web.task.UnlockSheepTask;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.OrderUtils;
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
@RequestMapping("/app/sheep_order")
public class SheepOrderController extends BaseSecretController {

    @Resource
    StSheepService sheepService;
    @Resource
    StClaimOrderService claimOrderService;


    @ResponseBody
    @RequestMapping(value = "/is_pay", method = RequestMethod.POST)
    public ResultView orderIsPay() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");

        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String oidStr = Utils.getSomeValue(map.get("order_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        String orderNum = Utils.getSomeValue(map.get("order_num"));

//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String oidStr = Utils.getSomeValue(request.getParameter("order_id"));
//        String sheepIdStr = Utils.getSomeValue(request.getParameter("sheep_id"));
//        String orderNum = Utils.getSomeValue(request.getParameter("order_num"));

        if (StringUtils.isBlank(uidStr) || StringUtils.isBlank(oidStr) || StringUtils.isBlank(sheepIdStr) || StringUtils.isBlank(orderNum)){
            return result(null, "参数错误");
        }

        long oid = Utils.parseLong(oidStr);
        long uid = Utils.parseLong(uidStr);
        long sid = Utils.parseLong(sheepIdStr);
        if (oid <= 0 || uid <= 0 || sid <= 0) return result(null, "参数错误");

        StSheepBO sheepBO = sheepService.selectByPrimaryKey(sid);
        if(sheepBO == null) return result(null, "不可支付");
        if(!(sheepBO.getOperateUid() == uid && sheepBO.getSheepStatus() == 2)) return result(null, "不可支付");
        StClaimOrderBO orderBO = claimOrderService.selectByPrimaryKey(oid);
        System.out.println(orderBO.toString());
        if(orderBO == null) return result(null, "不可支付");
        if(!(orderNum.equals(orderBO.getOrderNum()) && orderBO.getUserId() == uid && orderBO.getOrderStatus() == 0)) return result(null, "不可支付");
        JSONObject json = new JSONObject();
        json.put("is_pay","1");
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
//        return result(json);
    }


    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public ResultView claimOrderDetail() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");

        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String oidStr = Utils.getSomeValue(map.get("order_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));

//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String oidStr = Utils.getSomeValue(request.getParameter("order_id"));
//        String sheepIdStr = Utils.getSomeValue(request.getParameter("sheep_id"));

        if (StringUtils.isBlank(uidStr) || StringUtils.isBlank(oidStr) || StringUtils.isBlank(sheepIdStr)) return result(null, "参数错误");

        long oid = Utils.parseLong(oidStr);
        long uid = Utils.parseLong(uidStr);
        long sid = Utils.parseLong(sheepIdStr);
        if (oid <= 0 || uid <= 0 || sid <= 0) return result(null, "参数错误");

        StClaimOrderBO orderBO = claimOrderService.selectOrderDetail(oid);
        if(orderBO == null) return result(null, "订单异常");
        if(uid != orderBO.getUserId()) return result(null, "订单异常");
        if(sid != orderBO.getSheepId()) return result(null, "订单异常");
        JSONObject json = new JSONObject();
        json.put("detail", orderBO);

        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
//        return result(json);
    }


    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultView deleteClaimOrder() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");

        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String oidStr = Utils.getSomeValue(map.get("order_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));

//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String oidStr = Utils.getSomeValue(request.getParameter("order_id"));
//        String sheepIdStr = Utils.getSomeValue(request.getParameter("sheep_id"));


        if (StringUtils.isBlank(uidStr) || StringUtils.isBlank(oidStr) || StringUtils.isBlank(sheepIdStr)) return result(null, "参数错误");

        long oid = Utils.parseLong(oidStr);
        long uid = Utils.parseLong(uidStr);
        long sid = Utils.parseLong(sheepIdStr);
        if (oid <= 0 || uid <= 0 || sid <= 0) return result(null, "参数错误");

        StClaimOrderBO orderBO = claimOrderService.selectByPrimaryKey(oid);
        int orderStatus = orderBO.getOrderStatus();
        if (!(orderStatus == 2 || orderStatus == 3)) return result(null, "订单状态异常");
        long ouid = orderBO.getUserId();
        if (ouid != uid) return result(null, "订单状态异常");
        if (sid != orderBO.getSheepId()) return result(null, "订单状态异常");

        StClaimOrderBO update = new StClaimOrderBO();
        Date date = new Date();
        update.setId(oid);
        update.setOrderStatus(-1);
        update.setPayStatus(-1);
        update.setModifyTime(date);
        int row = claimOrderService.updateByPrimaryKeySelective(update);

        if (row > 0) {
            return result("操作成功");
        }

        return result("操作失败");
    }

    @ResponseBody
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ResultView cancelClaimOrder() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");

        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String oidStr = Utils.getSomeValue(map.get("order_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));


        if (StringUtils.isBlank(uidStr) || StringUtils.isBlank(oidStr) || StringUtils.isBlank(sheepIdStr)) return result(null, "参数错误");

        long oid = Utils.parseLong(oidStr);
        long uid = Utils.parseLong(uidStr);
        long sid = Utils.parseLong(sheepIdStr);
        if (oid <= 0 || uid <= 0 || sid <= 0) return result(null, "参数错误");

        StClaimOrderBO orderBO = claimOrderService.selectByPrimaryKey(oid);
        System.out.println(orderBO.toString());
        int orderStatus = orderBO.getOrderStatus();
        if (orderStatus != 0) return result(null, "订单状态异常");
        long ouid = orderBO.getUserId();
        if (ouid != uid) return result(null, "订单状态异常");
        if (sid != orderBO.getSheepId()) return result(null, "订单状态异常");

        StClaimOrderBO update = new StClaimOrderBO();
        Date date = new Date();
        update.setId(oid);
        update.setOrderStatus(3);
        update.setPayStatus(2);
        update.setModifyTime(date);
        int row = claimOrderService.updateByPrimaryKeySelective(update);

        if (row > 0) {
            return result("操作成功");
        }

        return result("操作失败");
    }

    @ResponseBody
    @RequestMapping(value = "/claim_order_list", method = RequestMethod.POST)
    public ResultView claimOrderList() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");

        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String pageStr = Utils.getSomeValue(map.get("page"));
        String typeStr = Utils.getSomeValue(map.get("type"));


//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String pageStr = Utils.getSomeValue(request.getParameter("page"));
//        String typeStr = Utils.getSomeValue(request.getParameter("type"));


        if (StringUtils.isBlank(uidStr)) return result(null, "参数错误");

        int page = 1;
        if (!StringUtils.isEmpty(pageStr)) {
            page = Utils.parseInt(pageStr);
        }
        int uid = Utils.parseInt(uidStr);
        if (uid <= 0) return result(null, "参数异常");
        if (page < 1) page = 1;
        int pageSize = 10;
        int type = -1;
        if(StringUtils.isNotBlank(typeStr)) type = Utils.parseInt(typeStr);

        Page<StClaimOrderBO> orderPage = claimOrderService.selectOrderListByUid(getPageRequest(page, pageSize), uid, type);
        List<StClaimOrderBO> list = orderPage.getContent();
        long total = orderPage.getTotalElements();
        int totalPages = orderPage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("orderList", list);
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);

        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
//        return result(json);
    }


    @ResponseBody
    @RequestMapping(value = "/confirm_order", method = RequestMethod.POST)
    public ResultView confirmOrder() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");

        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        String payPrice = Utils.getSomeValue(map.get("pay_price"));
        String serialNum = Utils.getSomeValue(map.get("serial_num"));
        String oidStr = Utils.getSomeValue(map.get("order_id"));
        String payTypeStr = Utils.getSomeValue(map.get("pay_type"));
        String orderNum = Utils.getSomeValue(map.get("order_num"));

        if (StringUtils.isBlank(uidStr) || StringUtils.isBlank(sheepIdStr) || StringUtils.isBlank(payPrice) || StringUtils.isBlank(serialNum) || StringUtils.isBlank(oidStr)
                || StringUtils.isBlank(payTypeStr)) {
            return result(null, "参数错误");
        }

        long uid = Utils.parseLong(uidStr);
        long sheepId = Utils.parseLong(sheepIdStr);
        long oid = Utils.parseLong(oidStr);
        int payType = Utils.parseInt(payTypeStr);

        if (uid < 1 || sheepId < 1 || oid < 1 || payType < 1) {
            return result(null, "参数错误");
        }

        StSheepBO sheepBO = sheepService.selectByPrimaryKey(sheepId);
        System.out.println(sheepBO.toString());
        if (sheepBO == null) {
            ResultView resultView = result(null, "不存在的羊或该信息已被删除");
            resultView.setResult("2");
            return resultView;
        }
        if ((sheepBO.getSheepStatus() != 2 && sheepBO.getSheepStatus() != 0) || sheepBO.getUserId() != null) {
            ResultView resultView = result(null, "羊的当前状态不可领取");
            resultView.setResult("3");
            return resultView;
        }

        StClaimOrderBO sbo = claimOrderService.selectByPrimaryKey(oid);
        if (sbo == null) {
            ResultView resultView = result(null, "订单不存在");
            resultView.setResult("4");
            return resultView;
        }

        Date date = new Date();
        StClaimOrderBO up = new StClaimOrderBO();
        up.setPayType(payType);
        up.setSerialNumber(serialNum);
        up.setPayPrice(payPrice);
        up.setPayStatus(1);
        up.setOrderStatus(1);
        up.setPayTime(date);
        up.setModifyTime(date);
        up.setId(oid);
        int orderRow = claimOrderService.updateByPrimaryKeySelective(up);
        if (orderRow < 1) return result(null, "订单修改失败");

        StSheepBO stSheepBO = new StSheepBO();
        stSheepBO.setId(sheepId);
        stSheepBO.setSheepStatus(1);
        stSheepBO.setUserId(uid);
        stSheepBO.setModifyTime(date);
        int sheepRow = sheepService.updateByPrimaryKeySelective(stSheepBO);
        if (sheepRow < 0) {
            return result(null, "订单修改失败");
        } else {
            // 将任务移除队列
            WorkManager.removeTask(uidStr, sheepId);
            JSONObject json = new JSONObject();
            json.put("sheep_id", sheepId);
            json.put("order_id", oid);
            json.put("order_num", orderNum);
            json.put("pay_price", payPrice);
            json.put("serial_num", serialNum);
            return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
        }
    }


    @ResponseBody
    @RequestMapping(value = "/put_order", method = RequestMethod.POST)
    public ResultView putOrder() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");

        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        String price = Utils.getSomeValue(map.get("price"));
        String address = Utils.getSomeValue(map.get("address"));
        String name = Utils.getSomeValue(map.get("name"));
        String phone = Utils.getSomeValue(map.get("phone"));
        String remark = Utils.getSomeValue(map.get("remark"));
        String orderNum = Utils.getSomeValue(map.get("order_num"));
        String claimOrderId = Utils.getSomeValue(map.get("claim_order_id"));

//        String uidStr = request.getParameter("user_id");
//        String sheepIdStr = request.getParameter("sheep_id");
//        String price = request.getParameter("price");
//        String address = request.getParameter("address");
//        String name = request.getParameter("name");
//        String phone = request.getParameter("phone");
//        String remark = request.getParameter("remark");
//        String orderNum = request.getParameter("order_num");
//        String claimOrderId = request.getParameter("claim_order_id");
        System.out.println("uidStr = " + uidStr + "  sheepIdStr = " + sheepIdStr + "  price = " + price + "  address = " + address + "  name = " + name + "   phone = " + phone +
                "   remark = " + remark);
        if (StringUtils.isBlank(sheepIdStr) || StringUtils.isBlank(uidStr) || StringUtils.isBlank(price) ||
                StringUtils.isBlank(address) || StringUtils.isBlank(name) || StringUtils.isBlank(phone)
                ) {
            return result(null, "参数错误");
        }

        long uid = Utils.parseLong(uidStr);
        long sheepId = Utils.parseLong(sheepIdStr);
        long orderId = 0;
        if (StringUtils.isNotBlank(claimOrderId)) {
            orderId = Utils.parseLong(claimOrderId);
        }
        if (uid < 1 || sheepId < 1) return result(null, "参数错误");

        StSheepBO sheepBO = sheepService.selectByPrimaryKey(sheepId);
        if (sheepBO == null) {
            ResultView resultView = result(null, "不存在的羊或该信息已被删除");
            resultView.setResult("2");
            return resultView;
        }
        if (sheepBO.getSheepStatus() != 2 || sheepBO.getUserId() != null) {
            ResultView resultView = result(null, "羊的当前状态不可领取");
            resultView.setResult("3");
            return resultView;
        }

        StClaimOrderBO bo = new StClaimOrderBO();
        Date date = new Date();
        if (StringUtils.isNotBlank(orderNum) || StringUtils.isNotBlank(claimOrderId)) {
            // 之前的订单重新支付
            if (StringUtils.isBlank(claimOrderId)) return result(null, "参数错误");
            if (StringUtils.isBlank(orderNum)) return result(null, "参数错误");
            if (orderId < 1) return result(null, "参数错误");
            StClaimOrderBO claimOrderBO = claimOrderService.selectByPrimaryKey(orderId);
            long operateUid = sheepBO.getOperateUid();
            if (claimOrderBO.getOrderStatus() != 0 || uid != operateUid) {
                ResultView resultView = result(null, "当前订单状态不可支付");
                resultView.setResult("4");
                return resultView;
            }
            if (StringUtils.isNotBlank(remark)) {
                bo.setRemark(remark);
            }
            bo.setModifyTime(date);
            bo.setId(orderId);
            int row = claimOrderService.updateByPrimaryKeySelective(bo);
            if (row > 0) {
                JSONObject json = new JSONObject();
                json.put("modify_time", sheepBO.getModifyTime());
                return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
            } else {
                return result(null, "请求失败");
            }
        } else {
            // 生成新的订单
            String getOrderNum = OrderUtils.getOrder();
            bo.setOrderNum(getOrderNum);
            bo.setUserId(uid);
            bo.setPrice(price);
            bo.setPayStatus(0);
            bo.setOrderStatus(0);
            bo.setSheepId(sheepId);
            bo.setAddress(address);
            bo.setName(name);
            bo.setPhone(phone);
            bo.setCreateTime(date);
            if (StringUtils.isNotBlank(remark)) {
                bo.setRemark(remark);
            }
            bo.setModifyTime(date);
            System.out.println("===================================");
            System.out.println(bo.toString());
            System.out.println("===================================");
            long id = claimOrderService.insertSelective(bo);
            if (id > 0) {
                JSONObject json = new JSONObject();
                json.put("modify_time", sheepBO.getModifyTime());
                json.put("order_num", getOrderNum);
                json.put("order_id", bo.getId());
                System.out.println(json.toJSONString());
                System.out.println("id = " + bo.getId());
                return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
            } else {
                return result(null, "创建失败");
            }
        }
    }


    @ResponseBody
    @RequestMapping(value = "/confirm_sheep", method = RequestMethod.POST)
    public ResultView lockSheep() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        String uidStr = Utils.getSomeValue(map.get("user_id"));
//        String sheepIdStr = Utils.getSomeValue(request.getParameter("sheep_id"));
//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));

        if (StringUtils.isBlank(sheepIdStr) || StringUtils.isBlank(uidStr)) return result(null, "参数错误");
        long sheepId = Utils.parseLong(sheepIdStr);
        long uid = Utils.parseLong(uidStr);
        if (sheepId <= 0 || uid <= 0) return result(null, "参数错误");
        StSheepBO sheepBO = sheepService.selectByPrimaryKey(sheepId);
        if (sheepBO == null) {
            return result(null, "信息不存在");
        }
        if (sheepBO.getUserId() != null && sheepBO.getUserId() > 0) {
            return getReView();
        } else if(sheepBO.getUserId() == null || sheepBO.getUserId() < 1){
            if (sheepBO.getSheepStatus() == 1) {
                return getReView();
            } else if(sheepBO.getSheepStatus() == 2 && sheepBO.getOperateUid() == uid){
                return result(AESUtil.encryptForBase64("请求成功", Constant.APP_REQUEST));
            } else if(sheepBO.getSheepStatus() == 2 && sheepBO.getOperateUid() != uid){
                return getReView();
            }
        }

        int row = sheepService.lockSheepById(sheepId, uid);
        if (row > 0) {
            WorkManager.unlock(uidStr, sheepId, UnlockSheepTask.class);
            return result(AESUtil.encryptForBase64("请求成功", Constant.APP_REQUEST));
        } else {
            return result(null, "请求失败");
        }
    }

    public ResultView getReView(){
        ResultView resultView = result(null, "羊的当前状态不可领取");
        resultView.setResult("2");
        return resultView;
    }

    @ResponseBody
    @RequestMapping("/remove")
    public ResultView remove() {
        String sheepIdStr = request.getParameter("sheep_id");
        String uidStr = request.getParameter("user_id");
        if (StringUtils.isBlank(sheepIdStr) || StringUtils.isBlank(uidStr)) return result(null, "参数错误");
        long sheepId = Utils.parseLong(sheepIdStr);
        long uid = Utils.parseLong(uidStr);
        if (sheepId <= 0 || uid <= 0) return result(null, "参数错误");
        WorkManager.removeTask(uidStr, sheepId);
        return result("");
    }




}
