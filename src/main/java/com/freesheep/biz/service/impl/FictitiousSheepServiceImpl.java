package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.FictitiousSheepDAO;
import com.freesheep.biz.model.FictitiousSheepBO;
import com.freesheep.biz.service.FictitiousSheepService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FictitiousSheepServiceImpl implements FictitiousSheepService {

    @Resource
    protected FictitiousSheepDAO sheepDAO;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(FictitiousSheepBO record) {
        return 0;
    }

    @Override
    public int insertSelective(FictitiousSheepBO record) {
        return 0;
    }

    @Override
    public FictitiousSheepBO selectByPrimaryKey(Long id) {
        return sheepDAO.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(FictitiousSheepBO record) {
        return sheepDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(FictitiousSheepBO record) {
        return 0;
    }

    @Override
    public long getClaimCount(long uid) {
        return sheepDAO.getClaimCount(uid);
    }

    @Override
    public Page<FictitiousSheepBO> selectSheepList(Pageable pageable) {
        return sheepDAO.getSheepList(pageable);
    }

    @Override
    public long sheepCountByUid(long userId) {
        return sheepDAO.sheepCountByUid(userId);
    }

    @Override
    public int lockSheepById(long id, long uid) {
        return sheepDAO.lockSheepById(id, uid);
    }

    @Override
    public int unLockSheepById(long id) {
        return sheepDAO.unLockSheepById(id);
    }

    @Override
    public List<FictitiousSheepBO> selectClaimList(long uid) {
        return sheepDAO.selectClaimList(uid);
    }

    @Override
    public FictitiousSheepBO selectSheepInfo(long id) {
        return sheepDAO.selectSheepInfo(id);
    }
}
