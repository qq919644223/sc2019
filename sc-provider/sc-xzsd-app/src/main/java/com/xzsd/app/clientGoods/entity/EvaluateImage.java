package com.xzsd.app.clientGoods.entity;

public class EvaluateImage {
    /**
     * 图片编号
     */
    private String imageCode;
    /**
     * 图片路径
     */
    private String imagePath;
    /**
     * 商品编号
     */
    private String pictureGoodsCode;
    /**
     * 用户编号
     */
    private String userId;
    public String getImageCode() { return imageCode; }

    public void setImageCode(String imageCode) { this.imageCode = imageCode; }

    public String getImagePath() { return imagePath; }

    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getPictureGoodsCode() { return pictureGoodsCode; }

    public void setPictureGoodsCode(String goodsCode) { this.pictureGoodsCode = pictureGoodsCode; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
