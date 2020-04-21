package com.xzsd.pc.goodsCategory.dao;


import com.xzsd.pc.goodsCategory.entity.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsCategoryDao {

    /**
     * 商品分类列表查询
     * @return
     */
    List<GoodsCategory> listCategory( );

    /**
     * 统计分类名称数量
     * @param goodsCategory
     * @return
     */
    int countCateName(GoodsCategory goodsCategory);

    /**
     * 商品分类新增
     * @param goodsCategory
     * @return
     */
    int addGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 商品分类修改
     * @param goodsCategory
     * @return
     */
    int updateGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 商品分类详情
     * @param cateId
     * @return
     */
    GoodsCategory findGoodsCategoryById(@Param("cateId") String cateId);

    /**
     * 检查该分类是否为别的分类的父级
     * @param goodsCategory
     * @return
     */
    String isParent(GoodsCategory goodsCategory);

    /**
     * 商品分类删除
     * @param goodsCategory
     * @return
     */
    int deleteGoodsCategory(GoodsCategory goodsCategory);
}
