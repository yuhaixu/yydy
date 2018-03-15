package com.freesheep.biz.model;

import java.util.Date;

public class OutgoingProductInfoBO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.outgoing_order_id
     *
     * @mbg.generated
     */
    private Long outgoingOrderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.order_num
     *
     * @mbg.generated
     */
    private String orderNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.product_id
     *
     * @mbg.generated
     */
    private Long productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.goods_code
     *
     * @mbg.generated
     */
    private String goodsCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.system_order_num
     *
     * @mbg.generated
     */
    private String systemOrderNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.send_num
     *
     * @mbg.generated
     */
    private String sendNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.actual_send_num
     *
     * @mbg.generated
     */
    private String actualSendNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.inventory_type
     *
     * @mbg.generated
     */
    private String inventoryType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.item_code
     *
     * @mbg.generated
     */
    private String itemCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.item_id
     *
     * @mbg.generated
     */
    private String itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outgoing_product_info.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.id
     *
     * @return the value of outgoing_product_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.id
     *
     * @param id the value for outgoing_product_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.outgoing_order_id
     *
     * @return the value of outgoing_product_info.outgoing_order_id
     *
     * @mbg.generated
     */
    public Long getOutgoingOrderId() {
        return outgoingOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.outgoing_order_id
     *
     * @param outgoingOrderId the value for outgoing_product_info.outgoing_order_id
     *
     * @mbg.generated
     */
    public void setOutgoingOrderId(Long outgoingOrderId) {
        this.outgoingOrderId = outgoingOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.order_num
     *
     * @return the value of outgoing_product_info.order_num
     *
     * @mbg.generated
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.order_num
     *
     * @param orderNum the value for outgoing_product_info.order_num
     *
     * @mbg.generated
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.product_id
     *
     * @return the value of outgoing_product_info.product_id
     *
     * @mbg.generated
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.product_id
     *
     * @param productId the value for outgoing_product_info.product_id
     *
     * @mbg.generated
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.goods_code
     *
     * @return the value of outgoing_product_info.goods_code
     *
     * @mbg.generated
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.goods_code
     *
     * @param goodsCode the value for outgoing_product_info.goods_code
     *
     * @mbg.generated
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.system_order_num
     *
     * @return the value of outgoing_product_info.system_order_num
     *
     * @mbg.generated
     */
    public String getSystemOrderNum() {
        return systemOrderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.system_order_num
     *
     * @param systemOrderNum the value for outgoing_product_info.system_order_num
     *
     * @mbg.generated
     */
    public void setSystemOrderNum(String systemOrderNum) {
        this.systemOrderNum = systemOrderNum == null ? null : systemOrderNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.send_num
     *
     * @return the value of outgoing_product_info.send_num
     *
     * @mbg.generated
     */
    public String getSendNum() {
        return sendNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.send_num
     *
     * @param sendNum the value for outgoing_product_info.send_num
     *
     * @mbg.generated
     */
    public void setSendNum(String sendNum) {
        this.sendNum = sendNum == null ? null : sendNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.actual_send_num
     *
     * @return the value of outgoing_product_info.actual_send_num
     *
     * @mbg.generated
     */
    public String getActualSendNum() {
        return actualSendNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.actual_send_num
     *
     * @param actualSendNum the value for outgoing_product_info.actual_send_num
     *
     * @mbg.generated
     */
    public void setActualSendNum(String actualSendNum) {
        this.actualSendNum = actualSendNum == null ? null : actualSendNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.inventory_type
     *
     * @return the value of outgoing_product_info.inventory_type
     *
     * @mbg.generated
     */
    public String getInventoryType() {
        return inventoryType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.inventory_type
     *
     * @param inventoryType the value for outgoing_product_info.inventory_type
     *
     * @mbg.generated
     */
    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType == null ? null : inventoryType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.item_code
     *
     * @return the value of outgoing_product_info.item_code
     *
     * @mbg.generated
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.item_code
     *
     * @param itemCode the value for outgoing_product_info.item_code
     *
     * @mbg.generated
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.item_id
     *
     * @return the value of outgoing_product_info.item_id
     *
     * @mbg.generated
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.item_id
     *
     * @param itemId the value for outgoing_product_info.item_id
     *
     * @mbg.generated
     */
    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.modify_time
     *
     * @return the value of outgoing_product_info.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.modify_time
     *
     * @param modifyTime the value for outgoing_product_info.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outgoing_product_info.create_time
     *
     * @return the value of outgoing_product_info.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outgoing_product_info.create_time
     *
     * @param createTime the value for outgoing_product_info.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}