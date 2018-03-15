package com.freesheep.biz.dao;

import com.freesheep.biz.model.AppPageImgBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AppPageImgDAO extends AbstractBaseDao<AppPageImgBO> {

    public List<AppPageImgBO> getAllInfo(){
        Map<String,Object> map = new HashMap<>();
        return selectList("selectAppPageImg", map);
    }

}
