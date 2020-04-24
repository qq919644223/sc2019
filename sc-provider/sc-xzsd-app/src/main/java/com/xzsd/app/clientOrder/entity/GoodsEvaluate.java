package com.xzsd.app.clientOrder.entity;

public class GoodsEvaluate {
    /**
     * 用户编号
     */
    private String userId;
    /**
     *  商品编号
     */
    private String goodsId;
    /**
     *  评价内容
     */
    private String evaluateContent;
    /**
     * 商品等级(0-5表示0-5颗星)
     */
    private int evaluateScore;
    /**
     * 评价编号
     */
    private String evaluateCode;
    /**
     * 商品评价等级
     */
    private double goodsEvaluateScore;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public int getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(int evaluateScore) {
        this.evaluateScore = evaluateScore;
    }

    public String getEvaluateCode() {
        return evaluateCode;
    }

    public void setEvaluateCode(String evaluateCode) {
        this.evaluateCode = evaluateCode;
    }

    public double getGoodsEvaluateScore() {
        return goodsEvaluateScore;
    }

    public void setGoodsEvaluateScore(double goodsEvaluateScore) {
        this.goodsEvaluateScore = goodsEvaluateScore;
    }
}
