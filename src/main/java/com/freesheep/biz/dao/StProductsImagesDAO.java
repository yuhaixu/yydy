package com.freesheep.biz.dao;

import com.freesheep.biz.model.StProductsImagesBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StProductsImagesDAO extends AbstractBaseDao<StProductsImagesBO> {

    public List<StProductsImagesBO> getImageList(long productId){
        Map<String, Object> map = new HashMap<>();
        map.put("pid", productId);
        return selectList("selectProductImageInfo", map);
    }

}
