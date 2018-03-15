package com.freesheep.biz.dao;

import com.freesheep.biz.model.ActivityImgBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ActivityImgDAO extends AbstractBaseDao<ActivityImgBO> {

    public List<ActivityImgBO> getAllInfo(){
        Map<String, Object> map = new HashMap<>();
        return selectList("getAllImgInfo", map);
    }

}
