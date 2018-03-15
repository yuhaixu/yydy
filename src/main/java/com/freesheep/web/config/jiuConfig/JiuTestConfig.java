package com.freesheep.web.config.jiuConfig;

public interface JiuTestConfig {
    // 出库单类型
    String ORDER_TYPE_JYCK = "JYCK";
    // 仓库编码
    String WAREHOUSE_CODE = "B01";
    // 订单来源平台编码
    String SOURCE_PLATFORM_CODE = "OTHER";
    // 货主编码
    String OWNER_CODE = "jhyd";
    String JIU_APP_KEY = "f737f586-119b-4359-9d26-081374b2881b";
    String JIU_APP_SECRET = "e73a6317-e41f-473d-b8fd-a8e57c1b6dba";
    String CUSTOMER_ID = "jhyd";
    String JIU_REQUEST_URL = "http://api.test.jiuyescm.com/v1/qimen/receive?";
    // logisticsCode 用户自提时的物流商编码
    String ZT_LOGISTICS_CODE = "KHZT";
    String LOGISTICS_CODE = "JIUYE";

}
