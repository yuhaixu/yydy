package com.freesheep.biz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class StMomentsMediaBO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_moments_media.id
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_moments_media.mid
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer mid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_moments_media.url
     *
     * @mbg.generated
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_moments_media.created
     *
     * @mbg.generated
     */
    @JsonIgnore
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column st_moments_media.modified
     *
     * @mbg.generated
     */
    @JsonIgnore
    private Date modified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_moments_media.id
     *
     * @return the value of st_moments_media.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_moments_media.id
     *
     * @param id the value for st_moments_media.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_moments_media.mid
     *
     * @return the value of st_moments_media.mid
     *
     * @mbg.generated
     */
    public Integer getMid() {
        return mid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_moments_media.mid
     *
     * @param mid the value for st_moments_media.mid
     *
     * @mbg.generated
     */
    public void setMid(Integer mid) {
        this.mid = mid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_moments_media.url
     *
     * @return the value of st_moments_media.url
     *
     * @mbg.generated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_moments_media.url
     *
     * @param url the value for st_moments_media.url
     *
     * @mbg.generated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_moments_media.created
     *
     * @return the value of st_moments_media.created
     *
     * @mbg.generated
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_moments_media.created
     *
     * @param created the value for st_moments_media.created
     *
     * @mbg.generated
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column st_moments_media.modified
     *
     * @return the value of st_moments_media.modified
     *
     * @mbg.generated
     */
    public Date getModified() {
        return modified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column st_moments_media.modified
     *
     * @param modified the value for st_moments_media.modified
     *
     * @mbg.generated
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "StMomentsMediaBO{" +
                "id=" + id +
                ", mid=" + mid +
                ", url='" + url + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}