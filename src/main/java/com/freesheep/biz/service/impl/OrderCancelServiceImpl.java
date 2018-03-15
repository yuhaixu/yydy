package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.OrderCancelDAO;
import com.freesheep.biz.model.OrderCancelBO;
import com.freesheep.biz.service.OrderCancelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderCancelServiceImpl implements OrderCancelService {

    @Resource
    OrderCancelDAO orderCancelDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(OrderCancelBO record) {
        return 0;
    }

    @Override
    public int insertSelective(OrderCancelBO record) {
        return orderCancelDAO.insertSelective(record);
    }

    @Override
    public OrderCancelBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(OrderCancelBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(OrderCancelBO record) {
        return 0;
    }
}
