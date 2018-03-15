package com.freesheep.biz.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class FictitiousDeviceInfoBO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fictitious_device_info.id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fictitious_device_info.device_id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fictitious_device_info.device_longitude
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String deviceLongitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fictitious_device_info.device_latitude
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String deviceLatitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fictitious_device_info.step
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long step;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fictitious_device_info.data_time
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date dataTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fictitious_device_info.create_time
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fictitious_device_info.modify_time
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fictitious_device_info.id
     *
     * @return the value of fictitious_device_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fictitious_device_info.id
     *
     * @param id the value for fictitious_device_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fictitious_device_info.device_id
     *
     * @return the value of fictitious_device_info.device_id
     *
     * @mbg.generated
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fictitious_device_info.device_id
     *
     * @param deviceId the value for fictitious_device_info.device_id
     *
     * @mbg.generated
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fictitious_device_info.device_longitude
     *
     * @return the value of fictitious_device_info.device_longitude
     *
     * @mbg.generated
     */
    public String getDeviceLongitude() {
        return deviceLongitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fictitious_device_info.device_longitude
     *
     * @param deviceLongitude the value for fictitious_device_info.device_longitude
     *
     * @mbg.generated
     */
    public void setDeviceLongitude(String deviceLongitude) {
        this.deviceLongitude = deviceLongitude == null ? null : deviceLongitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fictitious_device_info.device_latitude
     *
     * @return the value of fictitious_device_info.device_latitude
     *
     * @mbg.generated
     */
    public String getDeviceLatitude() {
        return deviceLatitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fictitious_device_info.device_latitude
     *
     * @param deviceLatitude the value for fictitious_device_info.device_latitude
     *
     * @mbg.generated
     */
    public void setDeviceLatitude(String deviceLatitude) {
        this.deviceLatitude = deviceLatitude == null ? null : deviceLatitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fictitious_device_info.step
     *
     * @return the value of fictitious_device_info.step
     *
     * @mbg.generated
     */
    public Long getStep() {
        return step;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fictitious_device_info.step
     *
     * @param step the value for fictitious_device_info.step
     *
     * @mbg.generated
     */
    public void setStep(Long step) {
        this.step = step;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fictitious_device_info.data_time
     *
     * @return the value of fictitious_device_info.data_time
     *
     * @mbg.generated
     */
    public Date getDataTime() {
        return dataTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fictitious_device_info.data_time
     *
     * @param dataTime the value for fictitious_device_info.data_time
     *
     * @mbg.generated
     */
    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fictitious_device_info.create_time
     *
     * @return the value of fictitious_device_info.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fictitious_device_info.create_time
     *
     * @param createTime the value for fictitious_device_info.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fictitious_device_info.modify_time
     *
     * @return the value of fictitious_device_info.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fictitious_device_info.modify_time
     *
     * @param modifyTime the value for fictitious_device_info.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}