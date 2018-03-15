package com.freesheep.web.config.jiuConfig;

public interface JiuServerConfig {

    // 出库单类型
    String ORDER_TYPE_JYCK = "JYCK";
    // 仓库编码
    String WAREHOUSE_CODE = "B01";
    // 订单来源平台编码
    String SOURCE_PLATFORM_CODE = "OTHER";
    // 货主编码
    String OWNER_CODE = "yydy";
    String JIU_APP_KEY = "aad44284-851a-4946-bbe5-a3540ad41cb2";
    String JIU_APP_SECRET = "5c875334-0981-4c3e-954e-fdc35eedd7b2";
    String CUSTOMER_ID = "yydy";
    String JIU_REQUEST_URL = "http://apix.jiuyescm.com/v1/qimen/receive?";
    // logisticsCode 用户自提时的物流商编码
    String ZT_LOGISTICS_CODE = "KHZT";
    String LOGISTICS_CODE = "JIUYE";

}
