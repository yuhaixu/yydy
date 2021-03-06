package com.freesheep.biz.jiuBean;

import java.util.List;

public class IntoWarehouseConfirmBean extends BaseBean {

    private DeliveryOrderBean deliveryOrder;
    private EntryOrderBean entryOrder;
    private List<OrderLineItemBean> orderLines;

    public DeliveryOrderBean getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrderBean deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public List<OrderLineItemBean> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineItemBean> orderLines) {
        this.orderLines = orderLines;
    }

    public EntryOrderBean getEntryOrder() {
        return entryOrder;
    }

    public void setEntryOrder(EntryOrderBean entryOrder) {
        this.entryOrder = entryOrder;
    }
}
