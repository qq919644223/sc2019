package com.xzsd.app.clientOrder.entity;

import java.util.List;

public class OrderEvaluate {
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 商品评价信息集合
     */
    private static List<GoodsEvaluate> evaluateList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<GoodsEvaluate> getEvaluateList() {
        return evaluateList;
    }

    public void setEvaluateList(List<GoodsEvaluate> evaluateList) {
        this.evaluateList = evaluateList;
    }
}
