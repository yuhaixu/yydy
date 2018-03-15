package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.ActivityImgDAO;
import com.freesheep.biz.model.ActivityImgBO;
import com.freesheep.biz.service.ActivityImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityImgServiceImpl implements ActivityImgService {

    @Resource
    protected ActivityImgDAO imgDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(ActivityImgBO record) {
        return 0;
    }

    @Override
    public int insertSelective(ActivityImgBO record) {
        return 0;
    }

    @Override
    public ActivityImgBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ActivityImgBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ActivityImgBO record) {
        return 0;
    }

    @Override
    public List<ActivityImgBO> getAllInfo() {
        return imgDAO.getAllInfo();
    }
}
