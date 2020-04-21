package com.xzsd.pc.order.entity;

public class OrderInfo {
    /**
     * 页码
     */
    private int pageSize;
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 购买数量
     */
    private String payNum;
    /**
     * 售价
     */
    private String sellingPrice;
    /**
     * 订单编码
     */
    private String orderCode;
    /**
     * 订单总价
     */
    private String orderPrice;
    /**
     * 订单状态
     * 0已下单，
     * 1已取消，
     * 2已到货，
     * 3已取货，
     * 4已完成未评价，
     * 5已完成已评价
     */
    private String orderStatus;
    /**
     * 门店编码
     */
    private String storeCode;
    /**
     * 下单人姓名
     */
    private String orderPerName;
    /**
     * 下单人手机号
     */
    private String orderPerPhone;
    /**
     * 确认收款时间
     */
    private String receiptTime;
    /**
     * 付款时间起
     */
    private String startPayTime;
    /**
     * 付款时间止
     */
    private String endPayTime;
    /**
     * 角色 0管理员 1店长 2司机 3客户
     */
    private int role;
    /**
     * 作废标记 0为存在，1为作废
     */
    private int isDeleted;
    /**
     * 创建时间
     */
    private String gmtCreate;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新时间
     */
    private String gmtModified;
    /**
     * 更新者
     */
    private String lastModifiedBy;
    /**
     * 版本号
     */
    private String version;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getOrderPerName() {
        return orderPerName;
    }

    public void setOrderPerName(String orderPerName) {
        this.orderPerName = orderPerName;
    }

    public String getOrderPerPhone() {
        return orderPerPhone;
    }

    public void setOrderPerPhone(String orderPerPhone) {
        this.orderPerPhone = orderPerPhone;
    }

    public String getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(String receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getStartPayTime() {
        return startPayTime;
    }

    public void setStartPayTime(String startPayTime) {
        this.startPayTime = startPayTime;
    }

    public String getEndPayTime() {
        return endPayTime;
    }

    public void setEndPayTime(String endPayTime) {
        this.endPayTime = endPayTime;
    }

    public int getRole() { return role; }

    public void setRole(int role) { this.role = role; }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
