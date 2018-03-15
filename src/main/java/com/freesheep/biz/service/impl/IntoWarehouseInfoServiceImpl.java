package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.IntoWarehouseInfoDAO;
import com.freesheep.biz.model.IntoWarehouseInfoBO;
import com.freesheep.biz.service.IntoWarehouseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IntoWarehouseInfoServiceImpl implements IntoWarehouseInfoService {

    @Resource
    IntoWarehouseInfoDAO intoWarehouseInfoDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(IntoWarehouseInfoBO record) {
        return 0;
    }

    @Override
    public int insertSelective(IntoWarehouseInfoBO record) {
        return intoWarehouseInfoDAO.insertSelective(record);
    }

    @Override
    public IntoWarehouseInfoBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(IntoWarehouseInfoBO record) {
        return intoWarehouseInfoDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(IntoWarehouseInfoBO record) {
        return 0;
    }

    @Override
    public IntoWarehouseInfoBO selectInfoByOrder(String orderNum, String goodsCode) {
        return intoWarehouseInfoDAO.selectInfoByOrder(orderNum, goodsCode);
    }
}
