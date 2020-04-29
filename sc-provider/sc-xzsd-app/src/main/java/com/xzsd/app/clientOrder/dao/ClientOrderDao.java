package com.xzsd.app.clientOrder.dao;

import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.entity.GoodsEvaluate;
import com.xzsd.app.clientOrder.entity.GoodsInfo;
import feign.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

@Mapper
public interface ClientOrderDao {

    /**
     * 检验用户是否绑定了门店邀请码
     * @param userId
     * @return
     */
    String findInviteCode(@Param("userId")String userId);

    /**
     * 检验订单中的商品是否还有库存
     * @param listGoodsId
     * @return 库存列表
     */
    List<ClientOrderInfo> countStock(@Param("listGoodsId") List<String> listGoodsId);

    /**
     * 新增订单
     * @param orderId
     * @param orderPrice
     * @param storeId
     * @param userId
     * @return
     */
    int addOrder(@Param("orderId") String orderId,@Param("orderPrice") String orderPrice,@Param("storeId") String storeId,@Param("userId") String userId,@Param("totalCount") int totalCount);

    /**
     * 新增订单详情
     * @param listOrder
     * @param storeId
     * @return
     */
    int addOrderDetail(@Param("listOrder") List<ClientOrderInfo> listOrder,@Param("storeId") String storeId);

    /**
     * 修改商品库存
     * @param listOrder
     * @return
     */
    int updateGoodsStock(@Param("listOrder") List<ClientOrderInfo> listOrder);

    /**
     * 删除购物车信息
     * @param listShopCartId 选中的购物车编号集合
     * @param userId 更新人
     * @return
     */
    int deleteCartGoods(@Param("listShopCartId") List<String> listShopCartId, @Param("userId") String userId);

    /**
     * 查询订单列表
     * @param clientOrderInfo
     * @return 订单列表
     */
    List<ClientOrderInfo> listOrderByPage(ClientOrderInfo clientOrderInfo);

    /**
     * 修改订单状态
     *  @param clientOrderInfo
     * @return 修改结果
     */
    int updateOrderState(ClientOrderInfo clientOrderInfo);

    /**
     * 查询该订单下每个商品的购买数量
     * @param clientOrderInfo
     * @return
     */
    List <ClientOrderInfo> findGoodsNum(ClientOrderInfo clientOrderInfo);

    /**
     * 取消订单后恢复商品库存
     * @param listGoodsNum
     * @return
     */
    int updateStock(@Param("listGoodsNum") List<ClientOrderInfo> listGoodsNum);

    /**
     * 查询订单详情
     * @param orderId 订单编号
     * @return 查询结果
     */
    ClientOrderInfo listOrderDeepen(@Param("orderId") String orderId);

    /**
     * 查询订单评价商品信息列表
     * @param orderId 订单编号
     * @return 查询结果
     */
    List<GoodsInfo> listGoodsForEvaluate(@Param("orderId") String orderId);

    /**
     * 新增商品评价
     * @param goodsEvaluateList
     * @return
     */
    int addGoodsEvaluate(@Param("goodsEvaluateList") List<GoodsEvaluate> goodsEvaluateList);

    /**
     * 评价完将订单状态改为已完成已评价
     */
    int updateStatus(@Param("orderId")String orderId,@Param("userId")String userId);

    /**
     * 计算评价后的商品星级
     * @param goodsEvaluateList
     * @return
     */
    List<Double> countScore(@Param("goodsEvaluateList") List<GoodsEvaluate> goodsEvaluateList);

    /**
     * 评价后更新商品评价星级
     * @param goodsEvaluateList
     * @return
     */
    int updateScore(@Param("goodsEvaluateList") List<GoodsEvaluate> goodsEvaluateList);

    /**
     * 评价后更新商品销售量
     * @param listGoodsNum
     * @return
     */
    int updateSaleCount(@Param("listGoodsNum") List<ClientOrderInfo> listGoodsNum);
}
