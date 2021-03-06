package com.freesheep.biz.model;

import java.util.Date;

public class ReturnOrderInfoBO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.return_order_id
     *
     * @mbg.generated
     */
    private Long returnOrderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.order_num
     *
     * @mbg.generated
     */
    private String orderNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.system_order_id
     *
     * @mbg.generated
     */
    private String systemOrderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.out_biz_code
     *
     * @mbg.generated
     */
    private String outBizCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.order_confirm_time
     *
     * @mbg.generated
     */
    private String orderConfirmTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.goods_code
     *
     * @mbg.generated
     */
    private String goodsCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.product_id
     *
     * @mbg.generated
     */
    private Long productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.inventory_type
     *
     * @mbg.generated
     */
    private String inventoryType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.plan_qty
     *
     * @mbg.generated
     */
    private String planQty;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.actual_qty
     *
     * @mbg.generated
     */
    private String actualQty;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column return_order_info.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.id
     *
     * @return the value of return_order_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.id
     *
     * @param id the value for return_order_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.return_order_id
     *
     * @return the value of return_order_info.return_order_id
     *
     * @mbg.generated
     */
    public Long getReturnOrderId() {
        return returnOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.return_order_id
     *
     * @param returnOrderId the value for return_order_info.return_order_id
     *
     * @mbg.generated
     */
    public void setReturnOrderId(Long returnOrderId) {
        this.returnOrderId = returnOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.order_num
     *
     * @return the value of return_order_info.order_num
     *
     * @mbg.generated
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.order_num
     *
     * @param orderNum the value for return_order_info.order_num
     *
     * @mbg.generated
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.system_order_id
     *
     * @return the value of return_order_info.system_order_id
     *
     * @mbg.generated
     */
    public String getSystemOrderId() {
        return systemOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.system_order_id
     *
     * @param systemOrderId the value for return_order_info.system_order_id
     *
     * @mbg.generated
     */
    public void setSystemOrderId(String systemOrderId) {
        this.systemOrderId = systemOrderId == null ? null : systemOrderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.out_biz_code
     *
     * @return the value of return_order_info.out_biz_code
     *
     * @mbg.generated
     */
    public String getOutBizCode() {
        return outBizCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.out_biz_code
     *
     * @param outBizCode the value for return_order_info.out_biz_code
     *
     * @mbg.generated
     */
    public void setOutBizCode(String outBizCode) {
        this.outBizCode = outBizCode == null ? null : outBizCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.order_confirm_time
     *
     * @return the value of return_order_info.order_confirm_time
     *
     * @mbg.generated
     */
    public String getOrderConfirmTime() {
        return orderConfirmTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.order_confirm_time
     *
     * @param orderConfirmTime the value for return_order_info.order_confirm_time
     *
     * @mbg.generated
     */
    public void setOrderConfirmTime(String orderConfirmTime) {
        this.orderConfirmTime = orderConfirmTime == null ? null : orderConfirmTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.goods_code
     *
     * @return the value of return_order_info.goods_code
     *
     * @mbg.generated
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.goods_code
     *
     * @param goodsCode the value for return_order_info.goods_code
     *
     * @mbg.generated
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.product_id
     *
     * @return the value of return_order_info.product_id
     *
     * @mbg.generated
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.product_id
     *
     * @param productId the value for return_order_info.product_id
     *
     * @mbg.generated
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.inventory_type
     *
     * @return the value of return_order_info.inventory_type
     *
     * @mbg.generated
     */
    public String getInventoryType() {
        return inventoryType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.inventory_type
     *
     * @param inventoryType the value for return_order_info.inventory_type
     *
     * @mbg.generated
     */
    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType == null ? null : inventoryType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.plan_qty
     *
     * @return the value of return_order_info.plan_qty
     *
     * @mbg.generated
     */
    public String getPlanQty() {
        return planQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.plan_qty
     *
     * @param planQty the value for return_order_info.plan_qty
     *
     * @mbg.generated
     */
    public void setPlanQty(String planQty) {
        this.planQty = planQty == null ? null : planQty.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.actual_qty
     *
     * @return the value of return_order_info.actual_qty
     *
     * @mbg.generated
     */
    public String getActualQty() {
        return actualQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.actual_qty
     *
     * @param actualQty the value for return_order_info.actual_qty
     *
     * @mbg.generated
     */
    public void setActualQty(String actualQty) {
        this.actualQty = actualQty == null ? null : actualQty.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.create_time
     *
     * @return the value of return_order_info.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.create_time
     *
     * @param createTime the value for return_order_info.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column return_order_info.modify_time
     *
     * @return the value of return_order_info.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column return_order_info.modify_time
     *
     * @param modifyTime the value for return_order_info.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}