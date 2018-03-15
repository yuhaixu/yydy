package com.freesheep.biz.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class StOrderAddressesBO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_order_addresses.id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_order_addresses.recipients
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String recipients;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_order_addresses.mobile
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_order_addresses.country
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String country;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_order_addresses.province
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String province;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_order_addresses.city
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_order_addresses.county
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String county;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_order_addresses.detail
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String detail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_order_addresses.zipcode
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String zipcode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_order_addresses.created
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_order_addresses.modified
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date modified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_order_addresses.id
     *
     * @return the value of st_order_addresses.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_order_addresses.id
     *
     * @param id the value for st_order_addresses.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_order_addresses.recipients
     *
     * @return the value of st_order_addresses.recipients
     *
     * @mbg.generated
     */
    public String getRecipients() {
        return recipients;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_order_addresses.recipients
     *
     * @param recipients the value for st_order_addresses.recipients
     *
     * @mbg.generated
     */
    public void setRecipients(String recipients) {
        this.recipients = recipients == null ? null : recipients.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_order_addresses.mobile
     *
     * @return the value of st_order_addresses.mobile
     *
     * @mbg.generated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_order_addresses.mobile
     *
     * @param mobile the value for st_order_addresses.mobile
     *
     * @mbg.generated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_order_addresses.country
     *
     * @return the value of st_order_addresses.country
     *
     * @mbg.generated
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_order_addresses.country
     *
     * @param country the value for st_order_addresses.country
     *
     * @mbg.generated
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_order_addresses.province
     *
     * @return the value of st_order_addresses.province
     *
     * @mbg.generated
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_order_addresses.province
     *
     * @param province the value for st_order_addresses.province
     *
     * @mbg.generated
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_order_addresses.city
     *
     * @return the value of st_order_addresses.city
     *
     * @mbg.generated
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_order_addresses.city
     *
     * @param city the value for st_order_addresses.city
     *
     * @mbg.generated
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_order_addresses.county
     *
     * @return the value of st_order_addresses.county
     *
     * @mbg.generated
     */
    public String getCounty() {
        return county;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_order_addresses.county
     *
     * @param county the value for st_order_addresses.county
     *
     * @mbg.generated
     */
    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_order_addresses.detail
     *
     * @return the value of st_order_addresses.detail
     *
     * @mbg.generated
     */
    public String getDetail() {
        return detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_order_addresses.detail
     *
     * @param detail the value for st_order_addresses.detail
     *
     * @mbg.generated
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_order_addresses.zipcode
     *
     * @return the value of st_order_addresses.zipcode
     *
     * @mbg.generated
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_order_addresses.zipcode
     *
     * @param zipcode the value for st_order_addresses.zipcode
     *
     * @mbg.generated
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_order_addresses.created
     *
     * @return the value of st_order_addresses.created
     *
     * @mbg.generated
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_order_addresses.created
     *
     * @param created the value for st_order_addresses.created
     *
     * @mbg.generated
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_order_addresses.modified
     *
     * @return the value of st_order_addresses.modified
     *
     * @mbg.generated
     */
    public Date getModified() {
        return modified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_order_addresses.modified
     *
     * @param modified the value for st_order_addresses.modified
     *
     * @mbg.generated
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }
}