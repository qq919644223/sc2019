package com.xzsd.pc.goodsCategory.entity;

public class GoodsSecondCategory {
    /**
     * 分类id
     */
    private String secondCateId;
    /**
     * 分类名称
     */
    private String secondCateName;
    /**
     * 层级
     */
    private String level;
    /**
     * 父级分类编码
     */
    private String parentCode;
    /**
     * 分类备注
     */
    private String cateRemark;
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

    public String getSecondCateId() {
        return secondCateId;
    }

    public void setSecondCateId(String secondCateId) {
        this.secondCateId = secondCateId;
    }

    public String getSecondCateName() {
        return secondCateName;
    }

    public void setSecondCateName(String secondCateName) {
        this.secondCateName = secondCateName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCateRemark() {
        return cateRemark;
    }

    public void setCateRemark(String cateRemark) {
        this.cateRemark = cateRemark;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
