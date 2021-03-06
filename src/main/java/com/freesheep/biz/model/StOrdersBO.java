package com.freesheep.biz.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

public class StOrdersBO {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String loginname;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String nickname;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<StProductsBO> productList;



    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String recipients;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String mobile;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String country;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String province;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String city;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String county;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String detail;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String zipcode;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int isZt;


    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.user_id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.addr_id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer addrId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.amount
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Float amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.pay_status
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer payStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.pay_type
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer payType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.order_status
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer orderStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.express_name
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String expressName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.express_code
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String expressCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.remark
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.created
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.modified
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date modified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_orders.deleted
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.id
     *
     * @return the value of st_orders.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.id
     *
     * @param id the value for st_orders.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.user_id
     *
     * @return the value of st_orders.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.user_id
     *
     * @param userId the value for st_orders.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.addr_id
     *
     * @return the value of st_orders.addr_id
     *
     * @mbg.generated
     */
    public Integer getAddrId() {
        return addrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.addr_id
     *
     * @param addrId the value for st_orders.addr_id
     *
     * @mbg.generated
     */
    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.amount
     *
     * @return the value of st_orders.amount
     *
     * @mbg.generated
     */
    public Float getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.amount
     *
     * @param amount the value for st_orders.amount
     *
     * @mbg.generated
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.pay_status
     *
     * @return the value of st_orders.pay_status
     *
     * @mbg.generated
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.pay_status
     *
     * @param payStatus the value for st_orders.pay_status
     *
     * @mbg.generated
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.pay_type
     *
     * @return the value of st_orders.pay_type
     *
     * @mbg.generated
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.pay_type
     *
     * @param payType the value for st_orders.pay_type
     *
     * @mbg.generated
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.order_status
     *
     * @return the value of st_orders.order_status
     *
     * @mbg.generated
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.order_status
     *
     * @param orderStatus the value for st_orders.order_status
     *
     * @mbg.generated
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.express_name
     *
     * @return the value of st_orders.express_name
     *
     * @mbg.generated
     */
    public String getExpressName() {
        return expressName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.express_name
     *
     * @param expressName the value for st_orders.express_name
     *
     * @mbg.generated
     */
    public void setExpressName(String expressName) {
        this.expressName = expressName == null ? null : expressName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.express_code
     *
     * @return the value of st_orders.express_code
     *
     * @mbg.generated
     */
    public String getExpressCode() {
        return expressCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.express_code
     *
     * @param expressCode the value for st_orders.express_code
     *
     * @mbg.generated
     */
    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode == null ? null : expressCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.remark
     *
     * @return the value of st_orders.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.remark
     *
     * @param remark the value for st_orders.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.created
     *
     * @return the value of st_orders.created
     *
     * @mbg.generated
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.created
     *
     * @param created the value for st_orders.created
     *
     * @mbg.generated
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.modified
     *
     * @return the value of st_orders.modified
     *
     * @mbg.generated
     */
    public Date getModified() {
        return modified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.modified
     *
     * @param modified the value for st_orders.modified
     *
     * @mbg.generated
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_orders.deleted
     *
     * @return the value of st_orders.deleted
     *
     * @mbg.generated
     */
    public Date getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_orders.deleted
     *
     * @param deleted the value for st_orders.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    public List<StProductsBO> getProductList() {
        return productList;
    }

    public void setProductList(List<StProductsBO> productList) {
        this.productList = productList;
    }


    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getIsZt() {
        return isZt;
    }

    public void setIsZt(int isZt) {
        this.isZt = isZt;
    }
}