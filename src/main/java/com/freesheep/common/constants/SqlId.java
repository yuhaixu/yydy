/*
 * Copyright (c)  2016-2017 Alibaba Group Holding Limited
 */

package com.freesheep.common.constants;


public interface SqlId {
    /* public String SQL_SELECT_COUNT = "selectCount";
     public String SQL_SELECT = "select";*/
    String SQL_SELECT_BY_ID = "selectByPrimaryKey";
    String SQL_UPDATE_BY_ID = "updateByPrimaryKey";
    String SQL_UPDATE_BY_ID_SELECTIVE = "updateByPrimaryKeySelective";
    String SQL_DELETE_ALL = "deleteAll";
    String SQL_DELETE_BY_ID = "deleteByPrimaryKey";
    String SQL_INSERT = "insert";
    String SQL_INSERT_SELECTIVE = "insertSelective";
    String SQL_SELECT_ALL = "selectAll";
    String SQL_SELECT_ALL_COUNT = "selectAllCount";
}
