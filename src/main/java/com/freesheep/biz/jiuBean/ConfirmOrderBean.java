package com.freesheep.biz.jiuBean;

import java.util.List;

public class ConfirmOrderBean extends BaseBean {

    private DeliveryOrderBean deliveryOrder;
    private List<OrderLineItemBean> orderLines;
    private List<PackageBean> packages;

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

    public List<PackageBean> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageBean> packages) {
        this.packages = packages;
    }

    @Override
    public String toString() {
        return "ConfirmOrderBean{" +
                "deliveryOrder=" + deliveryOrder +
                ", orderLines=" + orderLines +
                ", packages=" + packages +
                '}';
    }
}
