package com.freesheep.biz.jiuBean;

import java.util.List;

public class ReturnOrderCreateBean extends BaseBean {

    private DeliveryOrderBean deliveryOrder;
    private ReturnOrderBean returnOrder;
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

    public ReturnOrderBean getReturnOrder() {
        return returnOrder;
    }

    public void setReturnOrder(ReturnOrderBean returnOrder) {
        this.returnOrder = returnOrder;
    }
}
