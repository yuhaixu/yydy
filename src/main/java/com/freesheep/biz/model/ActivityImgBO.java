package com.freesheep.biz.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class ActivityImgBO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity_img.id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity_img.activity_id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long activityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity_img.url
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity_img.activity_type
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer activityType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity_img.activity_url
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String activityUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity_img.mark
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String mark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity_img.create_time
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity_img.modify_time
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity_img.id
     *
     * @return the value of activity_img.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity_img.id
     *
     * @param id the value for activity_img.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity_img.activity_id
     *
     * @return the value of activity_img.activity_id
     *
     * @mbg.generated
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity_img.activity_id
     *
     * @param activityId the value for activity_img.activity_id
     *
     * @mbg.generated
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity_img.url
     *
     * @return the value of activity_img.url
     *
     * @mbg.generated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity_img.url
     *
     * @param url the value for activity_img.url
     *
     * @mbg.generated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity_img.activity_type
     *
     * @return the value of activity_img.activity_type
     *
     * @mbg.generated
     */
    public Integer getActivityType() {
        return activityType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity_img.activity_type
     *
     * @param activityType the value for activity_img.activity_type
     *
     * @mbg.generated
     */
    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity_img.activity_url
     *
     * @return the value of activity_img.activity_url
     *
     * @mbg.generated
     */
    public String getActivityUrl() {
        return activityUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity_img.activity_url
     *
     * @param activityUrl the value for activity_img.activity_url
     *
     * @mbg.generated
     */
    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl == null ? null : activityUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity_img.mark
     *
     * @return the value of activity_img.mark
     *
     * @mbg.generated
     */
    public String getMark() {
        return mark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity_img.mark
     *
     * @param mark the value for activity_img.mark
     *
     * @mbg.generated
     */
    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity_img.create_time
     *
     * @return the value of activity_img.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity_img.create_time
     *
     * @param createTime the value for activity_img.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity_img.modify_time
     *
     * @return the value of activity_img.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity_img.modify_time
     *
     * @param modifyTime the value for activity_img.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}