package com.freesheep.biz.service;

import com.freesheep.biz.model.ReturnOrderBO;

public interface ReturnOrderService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table return_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table return_order
     *
     * @mbg.generated
     */
    int insert(ReturnOrderBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table return_order
     *
     * @mbg.generated
     */
    long insertSelective(ReturnOrderBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table return_order
     *
     * @mbg.generated
     */
    ReturnOrderBO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table return_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ReturnOrderBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table return_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ReturnOrderBO record);
}