package com.freesheep.biz.jiuBean;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@Accessors(chain = true)
public class OrderLineItemBean extends BaseBean {

    // 外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用
    private String outBizCode;
    // 单据行号
    private String orderLineNo;
    // 交易平台订单
    private String sourceOrderCode;
    // 交易平台子订单编码
    private String subSourceOrderCode;
    // 支付平台交易号
    private String payNo;
    // 货主编码
    private String ownerCode;
    // 商品编码
    private String itemCode;
    // 仓储系统商品编码
    private String itemId;
    // 库存类型
    private String inventoryType;
    // 商品名称
    private String itemName;
    // 交易平台商品编码
    private String extCode;
    // 应发商品数量
    private String planQty;
    // 零售价
    private String retailPrice;
    // 实际成交价
    private String actualPrice;
    // 单件商品折扣金额
    private String discountAmount;
    // 批次编码
    private String batchCode;
    // 生产日期
    private String productDate;
    // 过期日期
    private String expireDate;
    private String qrCode;
    // 实发商品数量
    private String actualQty;
    private String remark;


    private List<BatchBean> batchs;

    public List<BatchBean> getBatchs() {
        return batchs;
    }

    public void setBatchs(List<BatchBean> batchs) {
        this.batchs = batchs;
    }


    public String getActualQty() {
        return actualQty;
    }

    public void setActualQty(String actualQty) {
        this.actualQty = actualQty;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getOrderLineNo() {
        return orderLineNo;
    }

    public void setOrderLineNo(String orderLineNo) {
        this.orderLineNo = orderLineNo;
    }

    public String getSourceOrderCode() {
        return sourceOrderCode;
    }

    public void setSourceOrderCode(String sourceOrderCode) {
        this.sourceOrderCode = sourceOrderCode;
    }

    public String getSubSourceOrderCode() {
        return subSourceOrderCode;
    }

    public void setSubSourceOrderCode(String subSourceOrderCode) {
        this.subSourceOrderCode = subSourceOrderCode;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getExtCode() {
        return extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode = extCode;
    }

    public String getPlanQty() {
        return planQty;
    }

    public void setPlanQty(String planQty) {
        this.planQty = planQty;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getOutBizCode() {
        return outBizCode;
    }

    public void setOutBizCode(String outBizCode) {
        this.outBizCode = outBizCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "OrderLineItemBean{" +
                "outBizCode='" + outBizCode + '\'' +
                ", orderLineNo='" + orderLineNo + '\'' +
                ", sourceOrderCode='" + sourceOrderCode + '\'' +
                ", subSourceOrderCode='" + subSourceOrderCode + '\'' +
                ", payNo='" + payNo + '\'' +
                ", ownerCode='" + ownerCode + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemId='" + itemId + '\'' +
                ", inventoryType='" + inventoryType + '\'' +
                ", itemName='" + itemName + '\'' +
                ", extCode='" + extCode + '\'' +
                ", planQty='" + planQty + '\'' +
                ", retailPrice='" + retailPrice + '\'' +
                ", actualPrice='" + actualPrice + '\'' +
                ", discountAmount='" + discountAmount + '\'' +
                ", batchCode='" + batchCode + '\'' +
                ", productDate='" + productDate + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", actualQty='" + actualQty + '\'' +
                ", batchs=" + batchs +
                '}';
    }
}
