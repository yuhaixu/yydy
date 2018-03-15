package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.ProductCommentDAO;
import com.freesheep.biz.model.ProductCommentBO;
import com.freesheep.biz.service.ProductCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductCommentServiceImpl implements ProductCommentService {

    @Resource
    ProductCommentDAO commentDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(ProductCommentBO record) {
        return 0;
    }

    @Override
    public int insertSelective(ProductCommentBO record) {
        return commentDAO.insertSelective(record);
    }

    @Override
    public ProductCommentBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ProductCommentBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ProductCommentBO record) {
        return 0;
    }

    @Override
    public Page<ProductCommentBO> getCommentList(Pageable pageable, int pid) {
        return commentDAO.getCommentList(pageable, pid);
    }
}
