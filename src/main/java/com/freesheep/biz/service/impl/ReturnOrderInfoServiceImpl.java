package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.ReturnOrderInfoDAO;
import com.freesheep.biz.model.ReturnOrderInfoBO;
import com.freesheep.biz.service.ReturnOrderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReturnOrderInfoServiceImpl implements ReturnOrderInfoService {

    @Resource
    ReturnOrderInfoDAO returnOrderInfoDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(ReturnOrderInfoBO record) {
        return 0;
    }

    @Override
    public int insertSelective(ReturnOrderInfoBO record) {
        return returnOrderInfoDAO.insertSelective(record);
    }

    @Override
    public ReturnOrderInfoBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ReturnOrderInfoBO record) {
        return returnOrderInfoDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(ReturnOrderInfoBO record) {
        return 0;
    }

    @Override
    public ReturnOrderInfoBO selectReturnOrderByNum(String orderNum, String systemOrderNum, String goodsCode) {
        return returnOrderInfoDAO.selectReturnOrderByNum(orderNum, systemOrderNum, goodsCode);
    }


}
