package com.freesheep.biz.dao;

import com.freesheep.biz.model.StSheepBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StSheepDAO extends AbstractBaseDao<StSheepBO> {


    public Page<StSheepBO> getSheepList(Pageable pageable){
        Map<String, Object> map = new HashMap<>();
        return selectPageList("selectSheepList", map, pageable);
    }

    public Page<StSheepBO> getSheepUserList(Pageable pageable, int userId){
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userId);
        return selectPageList("selectSheepUserList", map, pageable);
    }


    public long sheepCountByUid(long userId){
        StSheepBO sheepBO = new StSheepBO();
        sheepBO.setUserId(userId);
        return selectCount("selectSheepCountByUid", sheepBO);
    }

    public StSheepBO getSheepInfoById(long userId , long sheepId){
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userId);
        map.put("sid", sheepId);
        return selectByMap("selectUserSheepInfo", map);
    }

    public int lockSheepById(long id, long uid){
        StSheepBO bo = new StSheepBO();
        bo.setId(id);
        bo.setSheepStatus(2);
        bo.setOperateUid(uid);
        bo.setModifyTime(new Date());
        return updateById(bo, "lockSheepById");
    }

    public int unLockSheepById(long id){
        StSheepBO bo = new StSheepBO();
        bo.setId(id);
        bo.setSheepStatus(0);
        bo.setModifyTime(new Date());
        return updateById(bo, "unlockSheepById");
    }

}
