package com.freesheep.biz.jiuBean;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Accessors(chain = true)
public class OrderCancelBean extends BaseBean {
    // 单据编码
    private String orderCode;
    // 仓库编码
    private String warehouseCode;
    // 货主编码
    private String ownerCode;
    // 单据类型, JYCK= 一般交易出库单，HHCK= 换货出库，BFCK= 补发出库PTCK=普通出库单，
    // DBCK=调拨出库，B2BRK=B2B入库，B2BCK=B2B出库，QTCK=其他出库，SCRK=生产入库，
    // LYRK=领用入库，CCRK=残次品入库，CGRK=采购入库 ，DBRK= 调拨入库 ，QTRK= 其他入库 ，
    // XTRK= 销退入库，THRK=退货入库，HHRK= 换货入库，CNJG= 仓内加工单，CGTH=采购退货出库单
    private String orderType;
    // 取消原因
    private String cancelReason;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
