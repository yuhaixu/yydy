package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.ProductShareDAO;
import com.freesheep.biz.model.ProductShareBO;
import com.freesheep.biz.service.ProductShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductShareServiceImpl implements ProductShareService {

    @Resource
    ProductShareDAO shareDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(ProductShareBO record) {
        return 0;
    }

    @Override
    public int insertSelective(ProductShareBO record) {
        return shareDAO.insertSelective(record);
    }

    @Override
    public ProductShareBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ProductShareBO record) {
        return shareDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProductShareBO record) {
        return 0;
    }

    @Override
    public ProductShareBO selectProductShare(long uid, long pid) {
        return shareDAO.selectProductShare(uid, pid);
    }
}
