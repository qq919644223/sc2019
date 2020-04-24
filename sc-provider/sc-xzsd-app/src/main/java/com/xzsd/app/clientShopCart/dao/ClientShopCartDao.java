package com.xzsd.app.clientShopCart.dao;

import com.xzsd.app.clientShopCart.entity.ClientShopCartInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClientShopCartDao {
    /**
     * 统计商品编号数量
     * @param clientShopCartInfo 购物车信息
     * @return
     */
    int countGoodsId(ClientShopCartInfo clientShopCartInfo);

    /**
     * 检验所选商品的库存
     * @param clientShopCartInfo
     * @return
     */
    int findStock(ClientShopCartInfo clientShopCartInfo);

    /**
     * 新增购物车
     * @param clientShopCartInfo 购物车信息
     * @return
     */
    int addShoppingCart(ClientShopCartInfo clientShopCartInfo);

    /**
     * 获取购物车所有商品信息
     * @param clientShopCartInfo
     * @return 购物车所有商品信息
     */
    List<ClientShopCartInfo> listShoppingCartsByPage(ClientShopCartInfo clientShopCartInfo);

    /**
     * 修改购物车
     * @param clientShopCartInfo 购物车信息
     * @return 修改结果
     */
    int updateShoppingCart(ClientShopCartInfo clientShopCartInfo);

    /**
     * 删除购物车信息
     * @param listCode 选中的购物车编号集合
     * @param userId 更新人
     * @return
     */
    int deleteShoppingCart(@Param("listCode") List<String> listCode, @Param("userId") String userId);

    /**
     * 修改时根据购物车编号查出商品库存
     * @param clientShopCartInfo
     * @return
     */
    int findGoodsStock(ClientShopCartInfo clientShopCartInfo);

}
