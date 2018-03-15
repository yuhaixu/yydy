package com.freesheep.biz.service;

import com.freesheep.biz.model.StMomentsMediaBO;

import java.util.List;

public interface StMomentsMediaService {
    List<StMomentsMediaBO> selectMediaForMid(int mid);
    void insertMediaList(List<StMomentsMediaBO> mediaBOList);
}