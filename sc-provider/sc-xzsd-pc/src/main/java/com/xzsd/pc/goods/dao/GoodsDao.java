package com.xzsd.pc.goods.dao;


import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goods.entity.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsDao {

    /**
     * 统计书号数量
     * @param goodsInfo 商品信息
     * @return
     */
    int countBookNo(GoodsInfo goodsInfo);
    /**
     * 新增商品
     * @param goodsInfo 商品信息
     * @return
     */
    int addGoods(GoodsInfo goodsInfo);

    /**
     * 删除商品信息
     * @param listGoodsCode 选中的商品编号集合
     * @param userId 更新人
     * @return
     */
    int deleteGoods(@Param("listGoodsCode") List<String> listGoodsCode, @Param("userId") String userId);

    /**
     * 修改商品信息
     * @param goodsInfo 商品信息
     * @return 修改结果
     */
    int updateGoodsById(GoodsInfo goodsInfo);

    /**
     * 获取所有商品信息
     * @param goodsInfo
     * @return 所有商品信息
     */
    List<GoodsInfo> listGoodsByPage(GoodsInfo goodsInfo);

    /**
     * 查询商品信息
     * @param goodsCode 商品编码
     * @return 查询结果
     */
    GoodsInfo findGoodsById(@Param("goodsCode") String goodsCode);

    /**
     * 修改商品状态
     *  @param listCode 选中的商品编号集合
     * @param goodsStatus
     * @param userId 更新人
     * @return 修改结果
     */
    int updateGoodsStatus(@Param("listCode") List<String> listCode, @Param("goodsStatus") int goodsStatus, @Param("userId") String userId);

    /**
     * 查询商品分类下拉框
     * @param classifyId
     * @return 商品分类列表
     */
    List<GoodsVO> listGoodsClassify(@Param("classifyId")String classifyId);


    List<String> listHotChart(@Param("listCode") List<String> listCode);
}
