package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.ProductCouponDAO;
import com.freesheep.biz.model.ProductCouponBO;
import com.freesheep.biz.service.ProductCouponService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductCouponServiceImpl implements ProductCouponService {

    @Resource
    ProductCouponDAO couponDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return couponDAO.deleteById(id);
    }

    @Override
    public int insert(ProductCouponBO record) {
        return 0;
    }

    @Override
    public int insertSelective(ProductCouponBO record) {
        return 0;
    }

    @Override
    public ProductCouponBO selectByPrimaryKey(Long id) {
        return couponDAO.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ProductCouponBO record) {
        return couponDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProductCouponBO record) {
        return 0;
    }

    @Override
    public Page<ProductCouponBO> getCouponList(Pageable pageable, long uid) {
        return couponDAO.getCouponList(pageable, uid);
    }

    @Override
    public ProductCouponBO getInfoByCode(String couponCode) {
        return couponDAO.selectInfoByCode(couponCode);
    }

    @Override
    public boolean insertCouponList(List<ProductCouponBO> couponList) {
        return couponDAO.insertCouponList(couponList);
    }
}
