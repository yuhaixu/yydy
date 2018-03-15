package com.freesheep.biz.service;

import com.freesheep.biz.model.StProductInfoBO;

public interface StProductInfoService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_product_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_product_info
     *
     * @mbg.generated
     */
    int insert(StProductInfoBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_product_info
     *
     * @mbg.generated
     */
    int insertSelective(StProductInfoBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_product_info
     *
     * @mbg.generated
     */
    StProductInfoBO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_product_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StProductInfoBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_product_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StProductInfoBO record);

    StProductInfoBO selectInfoByPid(long pid);
}