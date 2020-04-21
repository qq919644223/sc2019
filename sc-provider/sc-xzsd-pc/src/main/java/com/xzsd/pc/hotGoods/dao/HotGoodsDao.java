package com.xzsd.pc.hotGoods.dao;


import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotGoodsDao {
    /**
     * 获取所有热门位商品信息
     * @param hotGoodsInfo
     * @return 所有热门位商品信息
     */
    List<HotGoodsInfo> listHotGoodsByPage(HotGoodsInfo hotGoodsInfo);

    /**
     * 统计商品编码和排序号数量
     * @param hotGoodsInfo 商品信息
     * @return
     */
    int countCode(HotGoodsInfo hotGoodsInfo);

    /**
     * 新增商品
     * @param hotGoodsInfo 商品信息
     * @return
     */
    int addHotGoods(HotGoodsInfo hotGoodsInfo);

    /**
     * 修改热门位商品信息
     * @param hotGoodsInfo 热门位商品信息
     * @return 修改结果
     */
    int updateHotGoodsById(HotGoodsInfo hotGoodsInfo);

    /**
     * 删除热门位商品信息
     * @param listCode 选中的热门位商品编号集合
     * @param userId 更新人
     * @return
     */
    int deleteHotGoods(@Param("listCode") List<String> listCode, @Param("userId") String userId);

    /**
     * 查询热门位商品信息
     * @param hotGoodsId 热门商品编码
     * @return 查询结果
     */
    HotGoodsInfo findhotGoodsById(@Param("hotGoodsId") String hotGoodsId);

    /**
     * 查询商品列表信息
     * @param hotGoodsInfo
     * @return 所有商品信息
     */
    List<HotGoodsInfo> listSelectGoodsByPage(HotGoodsInfo hotGoodsInfo);

    /**
     * 查询热门位商品展示数量
     * @return 热门位商品展示数量
     */
    HotGoodsInfo findHotGoodsNum();

    /**
     * 修改热门位商品展示数量
     * @param hotGoodsNum 热门位商品展示数量
     * @return 修改结果
     */
    int updateHotGoodsNumber(@Param("hotGoodsNum") String hotGoodsNum);
}
