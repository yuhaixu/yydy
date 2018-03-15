package com.freesheep.biz.jiuBean;

import java.util.List;

public class OrderLineBean extends BaseBean {

    private List<OrderLineItemBean> orderLine;

    public List<OrderLineItemBean> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(List<OrderLineItemBean> orderLine) {
        this.orderLine = orderLine;
    }
}
