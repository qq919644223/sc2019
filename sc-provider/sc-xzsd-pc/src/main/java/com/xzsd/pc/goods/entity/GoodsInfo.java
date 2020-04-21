package com.xzsd.pc.goods.entity;



public class GoodsInfo  {
    /**
     * 页码
     */
    private int pageSize;
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 一级分类名称
     */
    private String firstCategoryName;
    /**
     * 二级分类名称
     */
    private String secondCategoryName;
    /**
     * 一级分类Id
     */
    private String firstCategoryId;
    /**
     * 二级分类Id
     */
    private String secondCategoryId;
    /**
     * 成本价
     */
    private Double costPrice;
    /**
     * 售价
     */
    private Double sellingPrice;
    /**
     * 广告词
     */
    private String advertisement;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版社
     */
    private String press;
    /**
     * 销售量
     */
    private String saleCnt;
    /**
     * 评价星级
     */
    private String level;
    /**
     * 商品介绍
     */
    private String goodsDetail;
    /**
     * 商品状态
     * 0售空、1在售、2已下架、3未发布
     */
    private String goodsStatus;
    /**
     * 库存
     */
    private String stock;
    /**
     * 浏览量
     */
    private String pageview;
    /**
     * isbn书号
     */
    private String bookNo;
    /**
     * 商家编码
     */
    private String storeCode;
    /**
     * 商家名称
     */
    private String storeName;
    /**
     * 上架时间
     */
    private String shelfTime;
    /**
     * 图片路径
     */
    private String imageUrl;
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

    public String getFirstCategoryName() {
        return firstCategoryName;
    }

    public void setFirstCategoryName(String firstCategoryName) {
        this.firstCategoryName = firstCategoryName;
    }

    public String getSecondCategoryName() {
        return secondCategoryName;
    }

    public void setSecondCategoryName(String secondCategoryName) {
        this.secondCategoryName = secondCategoryName;
    }

    public String getFirstCategoryId() {
        return firstCategoryId;
    }

    public void setFirstCategoryId(String firstCategoryId) {
        this.firstCategoryId = firstCategoryId;
    }

    public String getSecondCategoryId() {
        return secondCategoryId;
    }

    public void setSecondCategoryId(String secondCategoryId) {
        this.secondCategoryId = secondCategoryId;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getSaleCnt() {
        return saleCnt;
    }

    public void setSaleCnt(String saleCnt) {
        this.saleCnt = saleCnt;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPageview() {
        return pageview;
    }

    public void setPageview(String pageview) {
        this.pageview = pageview;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

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
    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getShelfTime() {
        return shelfTime;
    }

    public void setShelfTime(String shelfTime) {
        this.shelfTime = shelfTime;
    }
}
