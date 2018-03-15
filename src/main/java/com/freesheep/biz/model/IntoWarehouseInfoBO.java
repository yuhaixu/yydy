package com.freesheep.biz.model;

import java.util.Date;

public class IntoWarehouseInfoBO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.into_warehouse_order_id
     *
     * @mbg.generated
     */
    private Long intoWarehouseOrderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.order_num
     *
     * @mbg.generated
     */
    private String orderNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.product_id
     *
     * @mbg.generated
     */
    private Long productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.goods_code
     *
     * @mbg.generated
     */
    private String goodsCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.receive_num
     *
     * @mbg.generated
     */
    private String receiveNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.actual_receive_num
     *
     * @mbg.generated
     */
    private String actualReceiveNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.inventory_type
     *
     * @mbg.generated
     */
    private String inventoryType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.item_code
     *
     * @mbg.generated
     */
    private String itemCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.item_id
     *
     * @mbg.generated
     */
    private String itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.system_remark
     *
     * @mbg.generated
     */
    private String systemRemark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column into_warehouse_info.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.id
     *
     * @return the value of into_warehouse_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.id
     *
     * @param id the value for into_warehouse_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.into_warehouse_order_id
     *
     * @return the value of into_warehouse_info.into_warehouse_order_id
     *
     * @mbg.generated
     */
    public Long getIntoWarehouseOrderId() {
        return intoWarehouseOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.into_warehouse_order_id
     *
     * @param intoWarehouseOrderId the value for into_warehouse_info.into_warehouse_order_id
     *
     * @mbg.generated
     */
    public void setIntoWarehouseOrderId(Long intoWarehouseOrderId) {
        this.intoWarehouseOrderId = intoWarehouseOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.order_num
     *
     * @return the value of into_warehouse_info.order_num
     *
     * @mbg.generated
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.order_num
     *
     * @param orderNum the value for into_warehouse_info.order_num
     *
     * @mbg.generated
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.product_id
     *
     * @return the value of into_warehouse_info.product_id
     *
     * @mbg.generated
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.product_id
     *
     * @param productId the value for into_warehouse_info.product_id
     *
     * @mbg.generated
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.goods_code
     *
     * @return the value of into_warehouse_info.goods_code
     *
     * @mbg.generated
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.goods_code
     *
     * @param goodsCode the value for into_warehouse_info.goods_code
     *
     * @mbg.generated
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.receive_num
     *
     * @return the value of into_warehouse_info.receive_num
     *
     * @mbg.generated
     */
    public String getReceiveNum() {
        return receiveNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.receive_num
     *
     * @param receiveNum the value for into_warehouse_info.receive_num
     *
     * @mbg.generated
     */
    public void setReceiveNum(String receiveNum) {
        this.receiveNum = receiveNum == null ? null : receiveNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.actual_receive_num
     *
     * @return the value of into_warehouse_info.actual_receive_num
     *
     * @mbg.generated
     */
    public String getActualReceiveNum() {
        return actualReceiveNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.actual_receive_num
     *
     * @param actualReceiveNum the value for into_warehouse_info.actual_receive_num
     *
     * @mbg.generated
     */
    public void setActualReceiveNum(String actualReceiveNum) {
        this.actualReceiveNum = actualReceiveNum == null ? null : actualReceiveNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.inventory_type
     *
     * @return the value of into_warehouse_info.inventory_type
     *
     * @mbg.generated
     */
    public String getInventoryType() {
        return inventoryType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.inventory_type
     *
     * @param inventoryType the value for into_warehouse_info.inventory_type
     *
     * @mbg.generated
     */
    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType == null ? null : inventoryType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.item_code
     *
     * @return the value of into_warehouse_info.item_code
     *
     * @mbg.generated
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.item_code
     *
     * @param itemCode the value for into_warehouse_info.item_code
     *
     * @mbg.generated
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.item_id
     *
     * @return the value of into_warehouse_info.item_id
     *
     * @mbg.generated
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.item_id
     *
     * @param itemId the value for into_warehouse_info.item_id
     *
     * @mbg.generated
     */
    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.system_remark
     *
     * @return the value of into_warehouse_info.system_remark
     *
     * @mbg.generated
     */
    public String getSystemRemark() {
        return systemRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.system_remark
     *
     * @param systemRemark the value for into_warehouse_info.system_remark
     *
     * @mbg.generated
     */
    public void setSystemRemark(String systemRemark) {
        this.systemRemark = systemRemark == null ? null : systemRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.create_time
     *
     * @return the value of into_warehouse_info.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.create_time
     *
     * @param createTime the value for into_warehouse_info.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column into_warehouse_info.modify_time
     *
     * @return the value of into_warehouse_info.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column into_warehouse_info.modify_time
     *
     * @param modifyTime the value for into_warehouse_info.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}