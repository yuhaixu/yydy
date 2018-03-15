package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StFavoriteDAO;
import com.freesheep.biz.model.StFavoriteBO;
import com.freesheep.biz.service.StFavoriteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StFavoriteServiceImpl implements StFavoriteService {

    @Resource
    StFavoriteDAO favoriteDAO;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return favoriteDAO.deleteById(id);
    }

    @Override
    public int insert(StFavoriteBO record) {
        return 0;
    }

    @Override
    public int insertSelective(StFavoriteBO record) {
        return favoriteDAO.insertSelective(record);
    }

    @Override
    public StFavoriteBO selectByPrimaryKey(Integer id) {
        return favoriteDAO.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(StFavoriteBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StFavoriteBO record) {
        return 0;
    }

    @Override
    public Page<StFavoriteBO> selectCollectionList(Pageable pageable, long uid) {
        return favoriteDAO.selectCollectionList(pageable, uid);
    }

    @Override
    public long selectCollectionCount(int uid, int pid) {
        return favoriteDAO.selectCollectionCount(uid, pid);
    }
}
