package com.freesheep.biz.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class StCartBO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_cart.id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_cart.user_id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_cart.pr_id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer prId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_cart.order_id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_cart.number
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer number;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_cart.created
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_cart.modified
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date modified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_cart.id
     *
     * @return the value of st_cart.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_cart.id
     *
     * @param id the value for st_cart.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_cart.user_id
     *
     * @return the value of st_cart.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_cart.user_id
     *
     * @param userId the value for st_cart.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_cart.pr_id
     *
     * @return the value of st_cart.pr_id
     *
     * @mbg.generated
     */
    public Integer getPrId() {
        return prId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_cart.pr_id
     *
     * @param prId the value for st_cart.pr_id
     *
     * @mbg.generated
     */
    public void setPrId(Integer prId) {
        this.prId = prId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_cart.order_id
     *
     * @return the value of st_cart.order_id
     *
     * @mbg.generated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_cart.order_id
     *
     * @param orderId the value for st_cart.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_cart.number
     *
     * @return the value of st_cart.number
     *
     * @mbg.generated
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_cart.number
     *
     * @param number the value for st_cart.number
     *
     * @mbg.generated
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_cart.created
     *
     * @return the value of st_cart.created
     *
     * @mbg.generated
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_cart.created
     *
     * @param created the value for st_cart.created
     *
     * @mbg.generated
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_cart.modified
     *
     * @return the value of st_cart.modified
     *
     * @mbg.generated
     */
    public Date getModified() {
        return modified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_cart.modified
     *
     * @param modified the value for st_cart.modified
     *
     * @mbg.generated
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }
}