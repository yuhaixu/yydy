package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.AppPageImgDAO;
import com.freesheep.biz.model.AppPageImgBO;
import com.freesheep.biz.service.AppPageImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppPageImgServiceImpl implements AppPageImgService {

    @Resource
    protected AppPageImgDAO imgDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(AppPageImgBO record) {
        return 0;
    }

    @Override
    public int insertSelective(AppPageImgBO record) {
        return 0;
    }

    @Override
    public AppPageImgBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(AppPageImgBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(AppPageImgBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(AppPageImgBO record) {
        return 0;
    }

    @Override
    public List<AppPageImgBO> getAllInfo() {
        return imgDAO.getAllInfo();
    }
}
