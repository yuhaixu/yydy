package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StProductInfoDAO;
import com.freesheep.biz.model.StProductInfoBO;
import com.freesheep.biz.service.StProductInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StProductInfoServiceImpl implements StProductInfoService {

    @Resource
    StProductInfoDAO productInfoDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return productInfoDAO.deleteById(id);
    }

    @Override
    public int insert(StProductInfoBO record) {
        return 0;
    }

    @Override
    public int insertSelective(StProductInfoBO record) {
        return productInfoDAO.insertSelective(record);
    }

    @Override
    public StProductInfoBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(StProductInfoBO record) {
        return productInfoDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(StProductInfoBO record) {
        return 0;
    }

    @Override
    public StProductInfoBO selectInfoByPid(long pid) {
        return productInfoDAO.selectInfoByPid(pid);
    }
}
