package com.freesheep.common.extend;

import com.alibaba.druid.pool.DruidDataSource;
import com.freesheep.common.config.CommonConfig;

import java.sql.SQLException;

public class REDruidDataSource extends DruidDataSource {

    @Override
    public void init() throws SQLException {
        jdbcUrl = CommonConfig.JDBC_CONNECTION_URL_KEY_CENTER;
        password =CommonConfig.JDBC_PASSWORD_KEY_CENTER;
        username =CommonConfig.JDBC_USER_NAME_KEY_CENTER;
        super.init();
    }

    }
