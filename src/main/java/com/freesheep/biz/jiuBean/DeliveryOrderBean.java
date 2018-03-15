package com.freesheep.biz.jiuBean;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Accessors(chain = true)
public class DeliveryOrderBean extends BaseBean {
    // 出库单号
    private String deliveryOrderCode;
    // 原出库单号（ERP分配）换货出库时为必填
    private String preDeliveryOrderCode;
    // 原出库单号 换货出库时为必填
    private String preDeliveryOrderId;
    // 仓储系统出库单号
    private String deliveryOrderId;
    // 采购单号
    private String purchaseOrderCode;
    // 入库单号
    private String entryOrderCode;
    // 仓库编码
    private String warehouseCode;
    // 出库单类型
    private String orderType;
    // 订单标记，用字符串格式来表示订单标记列表
    private String orderFlag;
    // 订单来源平台编码
    private String sourcePlatformCode;
    // 订单来源平台名称
    private String sourcePlatformName;
    // 发货单创建时间, string (19) , YYYY-MM-DD HH:MM:SS
    private String createTime;
    // 要求出库时间
    private String scheduleDate;
    // 前台订单 (店铺订单) 创建时间 (下单时间)
    private String placeOrderTime;
    // 订单支付时间
    private String payTime;
    // 支付平台交易号
    private String payNo;
    // 店铺名称
    private String shopNick;
    // 卖家名称
    private String sellerNick;
    // 买家昵称
    private String buyerNick;
    // 订单总金额 (元)
    private String totalAmount;
    // 商品总金额 (元)
    private String itemAmount;
    // 订单折扣金额
    private String discountAmount;
    // 快递费用 (元)
    private String freight;
    // 应收金额 (元) , 消费者还需要支付多少（货到付款时消费者还需要支付多少约定使用这个字段）
    private String arAmount;
    // 已收金额 (元) , 消费者已经支付多少
    private String gotAmount;
    // COD服务费
    private String serviceFee;
    // 物流公司编码
    private String logisticsCode;
    // 物流公司名称
    private String logisticsName;
    // 运单号
    private String expressCode;
    // 快递区域编码, 大头笔信息
    private String logisticsAreaCode;


    // 出库单状态
    private String status;
    // 外部业务编码
    private String outBizCode;
    // 多次发货后确认时
    // 0 表示发货单最终状态确认；
    // 1 表示发货单中间状态确认；
    private String confirmType;
    // 订单完成时间
    private String orderConfirmTime;
    // 当前状态操作员编码
    private String operatorCode;
    // 当前状态操作员姓名
    private String operatorName;
    // 当前状态操作时间
    private String operateTime;
    // 是否紧急, Y/N, 默认为N
    private String isUrgency;
    // 是否需要发票, Y/N, 默认为N
    private String invoiceFlag;
    // 是否需要保险, Y/N, 默认为N
    private String insuranceFlag;
    // 买家留言
    private String buyerMessage;
    // 卖家留言
    private String sellerMessage;
    // 备注
    private String remark;

    private DeliveryRequirementsBean deliveryRequirements;
    private SenderInfoBean senderInfo;
    private ReceiverInfoBean receiverInfo;
    private PickerInfoBean pickerInfo;

    public String getIsUrgency() {
        return isUrgency;
    }

    public void setIsUrgency(String isUrgency) {
        this.isUrgency = isUrgency;
    }

    public String getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(String invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }

    public String getInsuranceFlag() {
        return insuranceFlag;
    }

    public void setInsuranceFlag(String insuranceFlag) {
        this.insuranceFlag = insuranceFlag;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getSellerMessage() {
        return sellerMessage;
    }

    public void setSellerMessage(String sellerMessage) {
        this.sellerMessage = sellerMessage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ReceiverInfoBean getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(ReceiverInfoBean receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public SenderInfoBean getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(SenderInfoBean senderInfo) {
        this.senderInfo = senderInfo;
    }

    public DeliveryRequirementsBean getDeliveryRequirements() {
        return deliveryRequirements;
    }

    public void setDeliveryRequirements(DeliveryRequirementsBean deliveryRequirements) {
        this.deliveryRequirements = deliveryRequirements;
    }

    public String getDeliveryOrderCode() {
        return deliveryOrderCode;
    }

    public void setDeliveryOrderCode(String deliveryOrderCode) {
        this.deliveryOrderCode = deliveryOrderCode;
    }

    public String getPreDeliveryOrderCode() {
        return preDeliveryOrderCode;
    }

    public void setPreDeliveryOrderCode(String preDeliveryOrderCode) {
        this.preDeliveryOrderCode = preDeliveryOrderCode;
    }

    public String getPreDeliveryOrderId() {
        return preDeliveryOrderId;
    }

    public void setPreDeliveryOrderId(String preDeliveryOrderId) {
        this.preDeliveryOrderId = preDeliveryOrderId;
    }

    public String getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }

    public String getSourcePlatformCode() {
        return sourcePlatformCode;
    }

    public void setSourcePlatformCode(String sourcePlatformCode) {
        this.sourcePlatformCode = sourcePlatformCode;
    }

    public String getSourcePlatformName() {
        return sourcePlatformName;
    }

    public void setSourcePlatformName(String sourcePlatformName) {
        this.sourcePlatformName = sourcePlatformName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPlaceOrderTime() {
        return placeOrderTime;
    }

    public void setPlaceOrderTime(String placeOrderTime) {
        this.placeOrderTime = placeOrderTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getShopNick() {
        return shopNick;
    }

    public void setShopNick(String shopNick) {
        this.shopNick = shopNick;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(String itemAmount) {
        this.itemAmount = itemAmount;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getArAmount() {
        return arAmount;
    }

    public void setArAmount(String arAmount) {
        this.arAmount = arAmount;
    }

    public String getGotAmount() {
        return gotAmount;
    }

    public void setGotAmount(String gotAmount) {
        this.gotAmount = gotAmount;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getLogisticsAreaCode() {
        return logisticsAreaCode;
    }

    public void setLogisticsAreaCode(String logisticsAreaCode) {
        this.logisticsAreaCode = logisticsAreaCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOutBizCode() {
        return outBizCode;
    }

    public void setOutBizCode(String outBizCode) {
        this.outBizCode = outBizCode;
    }

    public String getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(String confirmType) {
        this.confirmType = confirmType;
    }

    public String getOrderConfirmTime() {
        return orderConfirmTime;
    }

    public void setOrderConfirmTime(String orderConfirmTime) {
        this.orderConfirmTime = orderConfirmTime;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public PickerInfoBean getPickerInfo() {
        return pickerInfo;
    }

    public void setPickerInfo(PickerInfoBean pickerInfo) {
        this.pickerInfo = pickerInfo;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getEntryOrderCode() {
        return entryOrderCode;
    }

    public void setEntryOrderCode(String entryOrderCode) {
        this.entryOrderCode = entryOrderCode;
    }

    public String getPurchaseOrderCode() {
        return purchaseOrderCode;
    }

    public void setPurchaseOrderCode(String purchaseOrderCode) {
        this.purchaseOrderCode = purchaseOrderCode;
    }

    @Override
    public String toString() {
        return "DeliveryOrderBean{" +
                "deliveryOrderCode='" + deliveryOrderCode + '\'' +
                ", preDeliveryOrderCode='" + preDeliveryOrderCode + '\'' +
                ", preDeliveryOrderId='" + preDeliveryOrderId + '\'' +
                ", deliveryOrderId='" + deliveryOrderId + '\'' +
                ", purchaseOrderCode='" + purchaseOrderCode + '\'' +
                ", entryOrderCode='" + entryOrderCode + '\'' +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderFlag='" + orderFlag + '\'' +
                ", sourcePlatformCode='" + sourcePlatformCode + '\'' +
                ", sourcePlatformName='" + sourcePlatformName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", scheduleDate='" + scheduleDate + '\'' +
                ", placeOrderTime='" + placeOrderTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", payNo='" + payNo + '\'' +
                ", shopNick='" + shopNick + '\'' +
                ", sellerNick='" + sellerNick + '\'' +
                ", buyerNick='" + buyerNick + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", itemAmount='" + itemAmount + '\'' +
                ", discountAmount='" + discountAmount + '\'' +
                ", freight='" + freight + '\'' +
                ", arAmount='" + arAmount + '\'' +
                ", gotAmount='" + gotAmount + '\'' +
                ", serviceFee='" + serviceFee + '\'' +
                ", logisticsCode='" + logisticsCode + '\'' +
                ", logisticsName='" + logisticsName + '\'' +
                ", expressCode='" + expressCode + '\'' +
                ", logisticsAreaCode='" + logisticsAreaCode + '\'' +
                ", status='" + status + '\'' +
                ", outBizCode='" + outBizCode + '\'' +
                ", confirmType='" + confirmType + '\'' +
                ", orderConfirmTime='" + orderConfirmTime + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", operateTime='" + operateTime + '\'' +
                ", isUrgency='" + isUrgency + '\'' +
                ", invoiceFlag='" + invoiceFlag + '\'' +
                ", insuranceFlag='" + insuranceFlag + '\'' +
                ", buyerMessage='" + buyerMessage + '\'' +
                ", sellerMessage='" + sellerMessage + '\'' +
                ", remark='" + remark + '\'' +
                ", deliveryRequirements=" + deliveryRequirements +
                ", senderInfo=" + senderInfo +
                ", receiverInfo=" + receiverInfo +
                ", pickerInfo=" + pickerInfo +
                '}';
    }
}
