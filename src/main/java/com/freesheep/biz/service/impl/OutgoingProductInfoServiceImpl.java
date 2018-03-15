package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.OutgoingProductInfoDAO;
import com.freesheep.biz.model.OutgoingProductInfoBO;
import com.freesheep.biz.service.OutgoingProductInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OutgoingProductInfoServiceImpl implements OutgoingProductInfoService {

    @Resource
    OutgoingProductInfoDAO productInfoDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(OutgoingProductInfoBO record) {
        return 0;
    }

    @Override
    public int insertSelective(OutgoingProductInfoBO record) {
        return productInfoDAO.insertSelective(record);
    }

    @Override
    public OutgoingProductInfoBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(OutgoingProductInfoBO record) {
        return productInfoDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(OutgoingProductInfoBO record) {
        return 0;
    }

    @Override
    public OutgoingProductInfoBO selectInfoByOrder(String orderNum, String goodsCode) {
        return productInfoDAO.selectInfoByOrder(orderNum, goodsCode);
    }
}
