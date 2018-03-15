package com.freesheep.biz.jiuBean;

public class DeliveryRequirementsBean extends BaseBean {

    // 投递时延要求
    private String scheduleType;
    // 要求送达日期
    private String scheduleDay;
    // 投递时间范围要求 (开始时间)
    private String scheduleStartTime;
    // 投递时间范围要求
    private String scheduleEndTime;
    // 发货服务类型
    private String deliveryType;

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    public String getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public String getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(String scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }
}
