package com.xzsd.app.clientGoods.dao;

import com.xzsd.app.clientGoods.entity.ClientGoodsInfo;
import com.xzsd.app.clientGoods.entity.FirstGoodsClassify;
import com.xzsd.app.clientGoods.entity.GoodsEvaluates;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClientGoodsDao {
    /**
     * 查询商品信息
     * @param goodsId 商品编码
     * @return 查询结果
     */
    ClientGoodsInfo getGoods(@Param("goodsId")String goodsId);

    /**
     * 查询一级商品分类列表
     * @return 一级商品分类列表
     */
    List<FirstGoodsClassify> listOneGoodsClassify();

    /**
     * 查询二级商品分类以及商品
     * @param classifyId
     * @return 二级商品分类以及商品列表
     */
    List<FirstGoodsClassify> listGetClassGoods(@Param("classifyId") String classifyId);

    /**
     * 查询商品评价列表
     * @param goodsEvaluates
     * @return 商品评价列表
     */
    List<GoodsEvaluates> listGoodsEvaluatesByPage(GoodsEvaluates goodsEvaluates);
}
