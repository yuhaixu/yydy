package com.freesheep.biz.service;

import com.freesheep.biz.model.StOrdersBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StOrdersService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_orders
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_orders
     *
     * @mbg.generated
     */
    int insert(StOrdersBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_orders
     *
     * @mbg.generated
     */
    int insertSelective(StOrdersBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_orders
     *
     * @mbg.generated
     */
    StOrdersBO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_orders
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StOrdersBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table st_orders
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StOrdersBO record);

    Page<StOrdersBO> getOrderList(Pageable pageable, long uid, int type);
    Page<StOrdersBO> getOrderList(Pageable pageable, int type);
    StOrdersBO getOrderDetails(long oid);
}