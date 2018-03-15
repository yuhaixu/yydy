package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StProductsImagesDAO;
import com.freesheep.biz.model.StProductsImagesBO;
import com.freesheep.biz.service.StProductsImagesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StProductsImagesServiceImpl implements StProductsImagesService {

    @Resource
    StProductsImagesDAO productsImagesDAO;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return productsImagesDAO.deleteById(id);
    }

    @Override
    public int insert(StProductsImagesBO record) {
        return 0;
    }

    @Override
    public int insertSelective(StProductsImagesBO record) {
        return productsImagesDAO.insertSelective(record);
    }

    @Override
    public StProductsImagesBO selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(StProductsImagesBO record) {
        return productsImagesDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(StProductsImagesBO record) {
        return 0;
    }
}
