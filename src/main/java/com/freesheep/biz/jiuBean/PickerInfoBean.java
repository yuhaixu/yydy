package com.freesheep.biz.jiuBean;

public class PickerInfoBean extends BaseBean {

    // 公司名称
    private String company;
    private String name;
    private String tel;
    private String mobile;
    // 证件号
    private String id;
    // 车牌号
    private String carNo;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @Override
    public String toString() {
        return "PickerInfoBean{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", id='" + id + '\'' +
                ", carNo='" + carNo + '\'' +
                '}';
    }
}
