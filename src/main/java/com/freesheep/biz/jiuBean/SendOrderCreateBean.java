package com.freesheep.biz.jiuBean;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@Accessors(chain = true)
public class SendOrderCreateBean extends BaseBean {

    private DeliveryOrderBean deliveryOrder;
    private List<OrderLineItemBean> orderLines;

    public List<OrderLineItemBean> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineItemBean> orderLines) {
        this.orderLines = orderLines;
    }

    public DeliveryOrderBean getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrderBean deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }
}
