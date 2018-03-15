package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StSheepDAO;
import com.freesheep.biz.model.StSheepBO;
import com.freesheep.biz.service.StSheepService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StSheepServiceImpl implements StSheepService{

    @Resource
    StSheepDAO sheepDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(StSheepBO record) {
        return 0;
    }

    @Override
    public int insertSelective(StSheepBO record) {
        return 0;
    }

    @Override
    public StSheepBO selectByPrimaryKey(Long id) {
        return sheepDAO.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(StSheepBO record) {
        return sheepDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(StSheepBO record) {
        return 0;
    }

    @Override
    public long sheepCountByUid(long userId) {
        return sheepDAO.sheepCountByUid(userId);
    }

    @Override
    public Page<StSheepBO> selectSheepList(Pageable pageable) {
        return sheepDAO.getSheepList(pageable);
    }

    @Override
    public Page<StSheepBO> selectSheepUserList(Pageable pageable, int userId) {
        return sheepDAO.getSheepUserList(pageable, userId);
    }

    @Override
    public StSheepBO selectUserSheepInfo(long userId, long sheepId) {
        return sheepDAO.getSheepInfoById(userId, sheepId);
    }

    @Override
    public int lockSheepById(long id, long uid) {
        return sheepDAO.lockSheepById(id, uid);
    }

    @Override
    public int unLockSheepById(long id) {
        return sheepDAO.unLockSheepById(id);
    }

}
