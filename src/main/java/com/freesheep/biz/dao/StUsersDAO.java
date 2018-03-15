package com.freesheep.biz.dao;

import com.freesheep.biz.model.StUsersBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StUsersDAO extends AbstractBaseDao<StUsersBO> {

    public StUsersBO selectUserForWeb(int id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        StUsersBO bo = selectByMap("selectUserForWeb", map);
        return bo;
    }

    public List<StUsersBO> selectForCircleDigs(int mid){
        Map<String, Object> map = new HashMap<>();
        map.put("mid", mid);
        return selectList("selectForCircleDigs", map);
    }

}
