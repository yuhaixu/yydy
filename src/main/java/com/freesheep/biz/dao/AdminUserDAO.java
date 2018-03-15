package com.freesheep.biz.dao;

import com.freesheep.biz.model.AdminUserBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AdminUserDAO extends AbstractBaseDao<AdminUserBO> {

    public AdminUserBO selectByUserName(String userName){
        Map<String, Object> map = new HashMap<>();
        map.put("uname", userName);
        return selectByMap("selectByUserName", map);
    }

    public AdminUserBO selectByUserId(long uid){
        Map<String, Object> map = new HashMap<>();
        map.put("id", uid);
        return selectByMap("selectByUserId", map);
    }

    public Page<AdminUserBO> getAdminUserList(Pageable pageable){
        Map<String, Object> paramMap = new HashMap<>();
        return selectPageList("getAdminUserList", paramMap, pageable);
    }

}
