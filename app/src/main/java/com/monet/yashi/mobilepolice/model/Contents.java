package com.monet.yashi.mobilepolice.model;

/**
 * Created by Administrator on 2016/5/4.
 */
public class Contents {

    private String name;    //联系人姓名

    private String phoneNumber; //联系人电话

    public Contents() {
    }

    public Contents(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contents{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
