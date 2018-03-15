package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StPastureDAO;
import com.freesheep.biz.model.StPastureBO;
import com.freesheep.biz.service.StPastureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StPastureServiceImpl implements StPastureService {

    @Resource
    StPastureDAO pastureDAO;

    @Override
    public Page<StPastureBO> getPastureList(Pageable pageable) {
        return pastureDAO.getPastureList(pageable);
    }
}
