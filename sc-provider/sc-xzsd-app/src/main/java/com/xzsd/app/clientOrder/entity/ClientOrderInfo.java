package com.xzsd.app.clientOrder.entity;

import java.util.List;

public class ClientOrderInfo {
    /**
     * 页码
     */
    private int pageSize;
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 订单详情编号
     */
    private String orderDetailCode;
    /**
     * 商品编号
     */
    private String goodsId;
    /**
     * 商品价格
     */
    private String goodsPrice;
    /**
     * 商品总价
     */
    private String goodsTotalPrice;
    /**
     * 客户选择商品数量
     */
    private int clientGoodsNum;
    /**
     * 门店编号
     */
    private String storeId;
    /**
     * 订单状态编号：
     * 0已下单， 2待取货，4或5已完成（按钮对应的订单状态）
     */
    private String orderStateId;
    /**
     * 商品库存
     */
    private int stock;
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
     * 订单里全部商品信息
     */
    private List<GoodsInfo> goodsList;
    /**
     * 订单总价
     */
    private String orderAllCost;
    /**
     * 订单总商品数量
     */
    private String orderAllGoodsCount;
    /**
     * 版本号
     */
    private String version;
    public ClientOrderInfo(String orderDetailCode, String orderId, String userId, String goodsId, String goodsPrice, int clientGoodsNum,String goodsTotalPrice,int stock) {
        this.orderDetailCode = orderDetailCode;
        this.orderId = orderId;
        this.gmtCreate = userId;
        this.goodsId = goodsId;
        this.goodsPrice = goodsPrice;
        this.clientGoodsNum = clientGoodsNum;
        this.goodsTotalPrice = goodsTotalPrice;
        this.stock = stock;
    }
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDetailCode() {
        return orderDetailCode;
    }

    public void setOrderDetailCode(String orderDetailCode) {
        this.orderDetailCode = orderDetailCode;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(String goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public int getClientGoodsNum() {
        return clientGoodsNum;
    }

    public void setClientGoodsNum(int clientGoodsNum) {
        this.clientGoodsNum = clientGoodsNum;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getOrderStateId() {
        return orderStateId;
    }

    public void setOrderStateId(String orderStateId) {
        this.orderStateId = orderStateId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

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

    public List<GoodsInfo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsInfo> goodsList) {
        this.goodsList = goodsList;
    }

    public String getOrderAllCost() {
        return orderAllCost;
    }

    public void setOrderAllCost(String orderAllCost) {
        this.orderAllCost = orderAllCost;
    }

    public String getOrderAllGoodsCount() {
        return orderAllGoodsCount;
    }

    public void setOrderAllGoodsCount(String orderAllGoodsCount) {
        this.orderAllGoodsCount = orderAllGoodsCount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
