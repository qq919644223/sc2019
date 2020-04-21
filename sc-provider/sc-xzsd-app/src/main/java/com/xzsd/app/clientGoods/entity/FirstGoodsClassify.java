package com.xzsd.app.clientGoods.entity;

import java.util.List;

public class FirstGoodsClassify {
    /**
     * 一级商品分类编号
     */
    private String classifyId;
    /**
     * 一级商品分类名称
     */
    private String classifyName;
    /**
     * 二级商品分类信息集合
     */
    private List<SecondGoodsClassify> goodsList;

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public List<SecondGoodsClassify> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<SecondGoodsClassify> goodsList) {
        this.goodsList = goodsList;
    }
}
