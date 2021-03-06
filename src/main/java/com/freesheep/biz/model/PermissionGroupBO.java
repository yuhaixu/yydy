package com.freesheep.biz.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class PermissionGroupBO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission_group.id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission_group.group_name
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String groupName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission_group.claim_order
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer claimOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission_group.goods_order
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer goodsOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission_group.device_list
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer deviceList;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission_group.device_type
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer deviceType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission_group.device_status
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer deviceStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission_group.pasture_list
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer pastureList;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission_group.sheep_breeds_list
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer sheepBreedsList;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission_group.create_time
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission_group.modify_time
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission_group.id
     *
     * @return the value of permission_group.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission_group.id
     *
     * @param id the value for permission_group.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission_group.group_name
     *
     * @return the value of permission_group.group_name
     *
     * @mbg.generated
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission_group.group_name
     *
     * @param groupName the value for permission_group.group_name
     *
     * @mbg.generated
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission_group.claim_order
     *
     * @return the value of permission_group.claim_order
     *
     * @mbg.generated
     */
    public Integer getClaimOrder() {
        return claimOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission_group.claim_order
     *
     * @param claimOrder the value for permission_group.claim_order
     *
     * @mbg.generated
     */
    public void setClaimOrder(Integer claimOrder) {
        this.claimOrder = claimOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission_group.goods_order
     *
     * @return the value of permission_group.goods_order
     *
     * @mbg.generated
     */
    public Integer getGoodsOrder() {
        return goodsOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission_group.goods_order
     *
     * @param goodsOrder the value for permission_group.goods_order
     *
     * @mbg.generated
     */
    public void setGoodsOrder(Integer goodsOrder) {
        this.goodsOrder = goodsOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission_group.device_list
     *
     * @return the value of permission_group.device_list
     *
     * @mbg.generated
     */
    public Integer getDeviceList() {
        return deviceList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission_group.device_list
     *
     * @param deviceList the value for permission_group.device_list
     *
     * @mbg.generated
     */
    public void setDeviceList(Integer deviceList) {
        this.deviceList = deviceList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission_group.device_type
     *
     * @return the value of permission_group.device_type
     *
     * @mbg.generated
     */
    public Integer getDeviceType() {
        return deviceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission_group.device_type
     *
     * @param deviceType the value for permission_group.device_type
     *
     * @mbg.generated
     */
    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission_group.device_status
     *
     * @return the value of permission_group.device_status
     *
     * @mbg.generated
     */
    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission_group.device_status
     *
     * @param deviceStatus the value for permission_group.device_status
     *
     * @mbg.generated
     */
    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission_group.pasture_list
     *
     * @return the value of permission_group.pasture_list
     *
     * @mbg.generated
     */
    public Integer getPastureList() {
        return pastureList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission_group.pasture_list
     *
     * @param pastureList the value for permission_group.pasture_list
     *
     * @mbg.generated
     */
    public void setPastureList(Integer pastureList) {
        this.pastureList = pastureList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission_group.sheep_breeds_list
     *
     * @return the value of permission_group.sheep_breeds_list
     *
     * @mbg.generated
     */
    public Integer getSheepBreedsList() {
        return sheepBreedsList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission_group.sheep_breeds_list
     *
     * @param sheepBreedsList the value for permission_group.sheep_breeds_list
     *
     * @mbg.generated
     */
    public void setSheepBreedsList(Integer sheepBreedsList) {
        this.sheepBreedsList = sheepBreedsList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission_group.create_time
     *
     * @return the value of permission_group.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission_group.create_time
     *
     * @param createTime the value for permission_group.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission_group.modify_time
     *
     * @return the value of permission_group.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission_group.modify_time
     *
     * @param modifyTime the value for permission_group.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}