package com.freesheep.biz.dao;

import com.freesheep.biz.model.ProductShareBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductShareDAO extends AbstractBaseDao<ProductShareBO> {


    public ProductShareBO selectProductShare(long uid, long pid){
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("pid", pid);
        return selectByMap("selectProductShare", map);
    }

}
