package com.freesheep.biz.service;

import com.freesheep.biz.model.OutgoingOrderBO;

public interface OutgoingOrderService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outgoing_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outgoing_order
     *
     * @mbg.generated
     */
    int insert(OutgoingOrderBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outgoing_order
     *
     * @mbg.generated
     */
    long insertSelective(OutgoingOrderBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outgoing_order
     *
     * @mbg.generated
     */
    OutgoingOrderBO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outgoing_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OutgoingOrderBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outgoing_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OutgoingOrderBO record);

    OutgoingOrderBO selectOutgoingOrderByDeliveryOrderCode(String deliveryOrderCode);
}