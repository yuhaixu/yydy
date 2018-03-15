package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.OutgoingOrderDAO;
import com.freesheep.biz.model.OutgoingOrderBO;
import com.freesheep.biz.service.OutgoingOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OutgoingOrderServiceImpl implements OutgoingOrderService {

    @Resource
    OutgoingOrderDAO outgoingOrderDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(OutgoingOrderBO record) {
        return 0;
    }

    @Override
    public long insertSelective(OutgoingOrderBO record) {
        int low = outgoingOrderDAO.insertSelective(record);
        if (low < 1) return 0;
        return record.getId();
    }

    @Override
    public OutgoingOrderBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(OutgoingOrderBO record) {
        return outgoingOrderDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(OutgoingOrderBO record) {
        return 0;
    }

    @Override
    public OutgoingOrderBO selectOutgoingOrderByDeliveryOrderCode(String deliveryOrderCode) {
        return outgoingOrderDAO.selectOutgoingOrderByDeliveryOrderCode(deliveryOrderCode);
    }
}
