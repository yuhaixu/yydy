/*
 * Copyright (c)  2016-2017 Alibaba Group Holding Limited
 */

package com.freesheep.common.config;


import com.freesheep.common.util.Setting;

public interface CommonConfig {
    String JDBC_CONNECTION_URL_KEY_CENTER = Setting.getValue("jdbc.connection.url");
    String JDBC_USER_NAME_KEY_CENTER = Setting.getValue("jdbc.user.name");
    String JDBC_PASSWORD_KEY_CENTER = Setting.getValue("jdbc.password");
    //String METAQ_PRODUCER_GROUP_NAME = Setting.getValue("mq.producer.group.name");
    String OSS_END_POINT = Setting.getValue("oss.endpoint");
    String OSS_ACCESS_KEY_ID = Setting.getValue("oss.access.key.id");
    String OSS_ACCESS_KEY_SECRET = Setting.getValue("oss.access.key.secret");
    String OSS_BUCKET_NAME = Setting.getValue("oss.bucket.name");
    String OSS_CDN_DOMAIN = Setting.getValue("oss.cdn.domain");
    String ENVIRONMENT = Setting.getValue("environment");
}
