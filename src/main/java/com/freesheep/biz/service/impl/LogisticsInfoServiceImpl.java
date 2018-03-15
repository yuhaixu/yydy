package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.LogisticsInfoDAO;
import com.freesheep.biz.model.LogisticsInfoBO;
import com.freesheep.biz.service.LogisticsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogisticsInfoServiceImpl implements LogisticsInfoService {

    @Resource
    LogisticsInfoDAO dao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(LogisticsInfoBO record) {
        return 0;
    }

    @Override
    public int insertSelective(LogisticsInfoBO record) {
        return dao.insertSelective(record);
    }

    @Override
    public LogisticsInfoBO selectByPrimaryKey(Long id) {
        return dao.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(LogisticsInfoBO record) {
        return dao.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(LogisticsInfoBO record) {
        return 0;
    }

    @Override
    public LogisticsInfoBO selectInfoByDeliveryOrderId(String deliveryOrderId) {
        return dao.selectInfoByDeliveryOrderId(deliveryOrderId);
    }
}
