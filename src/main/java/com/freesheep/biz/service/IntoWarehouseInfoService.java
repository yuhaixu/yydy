package com.freesheep.biz.service;

import com.freesheep.biz.model.IntoWarehouseInfoBO;

public interface IntoWarehouseInfoService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table into_warehouse_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table into_warehouse_info
     *
     * @mbg.generated
     */
    int insert(IntoWarehouseInfoBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table into_warehouse_info
     *
     * @mbg.generated
     */
    int insertSelective(IntoWarehouseInfoBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table into_warehouse_info
     *
     * @mbg.generated
     */
    IntoWarehouseInfoBO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table into_warehouse_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(IntoWarehouseInfoBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table into_warehouse_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(IntoWarehouseInfoBO record);

    IntoWarehouseInfoBO selectInfoByOrder(String orderNum, String goodsCode);
}