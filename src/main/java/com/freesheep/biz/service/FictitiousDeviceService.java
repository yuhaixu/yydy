package com.freesheep.biz.service;

import com.freesheep.biz.model.FictitiousDeviceBO;

public interface FictitiousDeviceService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_device
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_device
     *
     * @mbg.generated
     */
    int insert(FictitiousDeviceBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_device
     *
     * @mbg.generated
     */
    int insertSelective(FictitiousDeviceBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_device
     *
     * @mbg.generated
     */
    FictitiousDeviceBO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_device
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FictitiousDeviceBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_device
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FictitiousDeviceBO record);
}