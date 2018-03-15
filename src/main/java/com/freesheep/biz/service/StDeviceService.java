package com.freesheep.biz.service;

import com.freesheep.biz.model.StDeviceBO;

public interface StDeviceService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_device
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_device
     *
     * @mbg.generated
     */
    int insert(StDeviceBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_device
     *
     * @mbg.generated
     */
    long insertSelective(StDeviceBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_device
     *
     * @mbg.generated
     */
    StDeviceBO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_device
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StDeviceBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_device
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StDeviceBO record);

    StDeviceBO selectByDeviceNum(String deviceNo);
}