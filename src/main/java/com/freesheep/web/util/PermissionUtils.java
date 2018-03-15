package com.freesheep.web.util;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.AdminUserBO;

public class PermissionUtils {

    public static JSONObject getPermissionJson(AdminUserBO bo){
        JSONObject json = new JSONObject();
        if(bo != null){
            json.put("claim_order",getDetailPermissionJson(bo.getClaimOrder()));
            json.put("goods_order",getDetailPermissionJson(bo.getGoodsOrder()));
            json.put("device_list",getDetailPermissionJson(bo.getDeviceList()));
            json.put("device_type",getDetailPermissionJson(bo.getDeviceType()));
            json.put("device_status",getDetailPermissionJson(bo.getDeviceStatus()));
            json.put("pasture_list",getDetailPermissionJson(bo.getPastureList()));
            json.put("sheep_breeds_list",getDetailPermissionJson(bo.getSheepBreedsList()));
        }
        return json;
    }

    public static JSONObject getDetailPermissionJson(int per){
        JSONObject json = new JSONObject();
        int create = 0;
        int delete = 0;
        int modify = 0;
        int select = 0;
        switch (per){
            case 7:
                create = 1;
                delete = 1;
                modify = 1;
                select = 1;
                break;
            case 6:
                create = 1;
                delete = 1;
                select = 1;
                break;
            case 5:
                create = 1;
                modify = 1;
                select = 1;
                break;
            case 4:
                create = 1;
                select = 1;
                break;
            case 3:
                delete = 1;
                modify = 1;
                select = 1;
                break;
            case 2:
                delete = 1;
                select = 1;
                break;
            case 1:
                modify = 1;
                select = 1;
                break;
        }
        json.put("create",create);
        json.put("delete",delete);
        json.put("modify",modify);
        json.put("select",select);
        json.put("permission",per);
        return json;
    }

}
