package com.xzsd.app.driverHome.entity;

public class DriverHomeInfo {
    /**
     * 门店编号
     */
    private String storeId;
    /**
     * 门店信息
     */
    private String storeName;
    /**
     * 门店地址
     */
    private String address;
    /**
     * 店长名称
     */
    private String userName;
    /**
     * 店长电话
     */
    private String phone;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
