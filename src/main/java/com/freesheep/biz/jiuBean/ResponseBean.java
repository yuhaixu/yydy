package com.freesheep.biz.jiuBean;

import java.util.List;

public class ResponseBean {

    private String flag;
    private String code;
    private String message;
    private String deliveryOrderId;
    private String entryOrderId;
    private String itemId;
    // 仓储系统退货单编码
    private String returnOrderId;
    private List<ItemBean> items;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public String getEntryOrderId() {
        return entryOrderId;
    }

    public void setEntryOrderId(String entryOrderId) {
        this.entryOrderId = entryOrderId;
    }

    public String getReturnOrderId() {
        return returnOrderId;
    }

    public void setReturnOrderId(String returnOrderId) {
        this.returnOrderId = returnOrderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<ItemBean> getItems() {
        return items;
    }

    public void setItems(List<ItemBean> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "flag='" + flag + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", deliveryOrderId='" + deliveryOrderId + '\'' +
                ", entryOrderId='" + entryOrderId + '\'' +
                '}';
    }
}
