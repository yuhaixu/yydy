package com.freesheep.biz.dao;

import com.freesheep.biz.model.ProductCouponBO;
import com.freesheep.biz.model.StMomentsMediaBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductCouponDAO extends AbstractBaseDao<ProductCouponBO> {


    public Page<ProductCouponBO> getCouponList(Pageable pageable, long uid){
        Map<String, Object> map = new HashedMap();
        map.put("uid", uid);
        return selectPageList("selectCouponList", map, pageable);
    }

    public ProductCouponBO selectInfoByCode(String couponCode){
        Map<String, Object> map = new HashedMap();
        map.put("code", couponCode);
        return selectByMap("selectInfoByCode", map);
    }

    public boolean insertCouponList(List<ProductCouponBO> couponList){
        int row = getSqlSessionTemplate().insert("saveCouponInfoByList", couponList);
        System.out.println("插入的行数 ： " + row);
        return row > 0;
    }


}
