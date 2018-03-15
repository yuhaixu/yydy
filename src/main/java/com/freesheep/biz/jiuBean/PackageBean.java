package com.freesheep.biz.jiuBean;

import java.util.List;

public class PackageBean extends BaseBean {

    // 物流公司编码
    private String logisticsCode;
    // 物流公司名称
    private String logisticsName;
    // 运单号
    private String expressCode;
    // 包裹编号
    private String packageCode;
    // 包裹长度 (厘米)
    private String length;
    private String width;
    private String height;
    // 包裹理论重量 (千克)
    private String theoreticalWeight;
    // 包裹重量 (千克)
    private String weight;
    // 包裹体积 (升, L)
    private String volume;
    // 发票号
    private String invoiceNo;
    private List<ItemBean> items;


    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getTheoreticalWeight() {
        return theoreticalWeight;
    }

    public void setTheoreticalWeight(String theoreticalWeight) {
        this.theoreticalWeight = theoreticalWeight;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public List<ItemBean> getItems() {
        return items;
    }

    public void setItems(List<ItemBean> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "PackageBean{" +
                "logisticsCode='" + logisticsCode + '\'' +
                ", logisticsName='" + logisticsName + '\'' +
                ", expressCode='" + expressCode + '\'' +
                ", packageCode='" + packageCode + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", theoreticalWeight='" + theoreticalWeight + '\'' +
                ", weight='" + weight + '\'' +
                ", volume='" + volume + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", items=" + items +
                '}';
    }
}
