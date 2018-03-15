package com.freesheep.biz.service;

import com.freesheep.biz.model.StUsersBO;

import java.util.List;

public interface StUsersService {
    StUsersBO selectUserForWeb(int id);
    List<StUsersBO> selectCircleUserList(int mid);
}