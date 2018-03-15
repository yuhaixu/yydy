package com.freesheep.biz.jiuBean;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@Accessors(chain = true)
public class ConfirmSendGoodsBean extends BaseBean {

    private DeliveryOrder deliveryOrder;

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public class DeliveryOrder{
        // 出库单号
        private String deliveryOrderCode;
        // 仓储系统出库单号
        private String deliveryOrderId;
        // 仓库编码
        private String warehouseCode;
        // 出库单类型
        private String orderType;
        // 出库单状态
        private String status;
        // 外部业务编码
        private String outBizCode;
        // 多次发货后确认时
        // 0 表示发货单最终状态确认；
        // 1 表示发货单中间状态确认；
        private String confirmType;
        // 订单完成时间
        private String orderConfirmTime;
        // 当前状态操作员编码
        private String operatorCode;
        // 当前状态操作员姓名
        private String operatorName;
        // 当前状态操作时间
        private String operateTime;
        @XStreamImplicit(itemFieldName="invoices")
        private List<Invoice> invoices;

        public class Invoice{
            // 发票抬头
            private String header;
            // 发票金额
            private String amount;
            // 发票内容
            private String content;
            @XStreamImplicit(itemFieldName="detail")
            private List<Detail> details;


            public class Detail{
                private List<Items> list;

                public List<Items> getList() {
                    return list;
                }

                public void setList(List<Items> list) {
                    this.list = list;
                }

                public class Items{
                    // 商品名称
                    private String itemName;
                    // 商品单价
                    private String unit;
                    // 数量
                    private String quantity;
                    // 金额
                    private String amount;

                    public String getItemName() {
                        return itemName;
                    }

                    public void setItemName(String itemName) {
                        this.itemName = itemName;
                    }

                    public String getUnit() {
                        return unit;
                    }

                    public void setUnit(String unit) {
                        this.unit = unit;
                    }

                    public String getQuantity() {
                        return quantity;
                    }

                    public void setQuantity(String quantity) {
                        this.quantity = quantity;
                    }

                    public String getAmount() {
                        return amount;
                    }

                    public void setAmount(String amount) {
                        this.amount = amount;
                    }
                }

            }

            public List<Detail> getDetails() {
                return details;
            }

            public void setDetails(List<Detail> details) {
                this.details = details;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public List<Invoice> getInvoices() {
            return invoices;
        }

        public void setInvoices(List<Invoice> invoices) {
            this.invoices = invoices;
        }

        public String getDeliveryOrderCode() {
            return deliveryOrderCode;
        }

        public void setDeliveryOrderCode(String deliveryOrderCode) {
            this.deliveryOrderCode = deliveryOrderCode;
        }

        public String getDeliveryOrderId() {
            return deliveryOrderId;
        }

        public void setDeliveryOrderId(String deliveryOrderId) {
            this.deliveryOrderId = deliveryOrderId;
        }

        public String getWarehouseCode() {
            return warehouseCode;
        }

        public void setWarehouseCode(String warehouseCode) {
            this.warehouseCode = warehouseCode;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOutBizCode() {
            return outBizCode;
        }

        public void setOutBizCode(String outBizCode) {
            this.outBizCode = outBizCode;
        }

        public String getConfirmType() {
            return confirmType;
        }

        public void setConfirmType(String confirmType) {
            this.confirmType = confirmType;
        }

        public String getOrderConfirmTime() {
            return orderConfirmTime;
        }

        public void setOrderConfirmTime(String orderConfirmTime) {
            this.orderConfirmTime = orderConfirmTime;
        }

        public String getOperatorCode() {
            return operatorCode;
        }

        public void setOperatorCode(String operatorCode) {
            this.operatorCode = operatorCode;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public String getOperateTime() {
            return operateTime;
        }

        public void setOperateTime(String operateTime) {
            this.operateTime = operateTime;
        }
    }

}
