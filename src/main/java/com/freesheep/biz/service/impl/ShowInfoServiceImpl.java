package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.ShowInfoDAO;
import com.freesheep.biz.dao.StMomentsDAO;
import com.freesheep.biz.dao.StProductsDAO;
import com.freesheep.biz.model.ShowInfoBO;
import com.freesheep.biz.model.StMomentsBO;
import com.freesheep.biz.model.StProductsBO;
import com.freesheep.biz.service.ShowInfoService;
import com.freesheep.web.util.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowInfoServiceImpl implements ShowInfoService {

    @Resource
    protected ShowInfoDAO showInfoDAO;
    @Resource
    protected StProductsDAO productsDAO;
    @Resource
    protected StMomentsDAO momentsDAO;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(ShowInfoBO record) {
        return 0;
    }

    @Override
    public int insertSelective(ShowInfoBO record) {
        return 0;
    }

    @Override
    public ShowInfoBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ShowInfoBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ShowInfoBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ShowInfoBO record) {
        return 0;
    }

    @Override
    public ShowInfoBO getShowInfo() {
        List<ShowInfoBO> list = showInfoDAO.getShowInfo();
        List<Integer> productsIdList = new ArrayList<>();
        List<Integer> momentsIdList = new ArrayList<>();
        if(list == null || list.size() == 0) return null;
        for (int i = 0; i < list.size() ; i++){
            ShowInfoBO bo = list.get(i);
            int type = bo.getInfoType();
            if(type == 1){
                productsIdList.add(Utils.parseInt(bo.getShowId()));
            } else if(type == 2){
                momentsIdList.add(Utils.parseInt(bo.getShowId()));
            }
        }

        ShowInfoBO infoBO = new ShowInfoBO();
        List<StMomentsBO> momentList = momentsDAO.selectMomentForHome(momentsIdList);
        List<StProductsBO> productsList = productsDAO.selectProductForHome(productsIdList);
        infoBO.setMomentsList(momentList);
        infoBO.setProductsList(productsList);

        return infoBO;
    }
}
