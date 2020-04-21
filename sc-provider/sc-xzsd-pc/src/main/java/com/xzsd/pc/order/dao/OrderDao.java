package com.xzsd.pc.order.dao;

import com.xzsd.pc.order.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {
    /**
     * 获取所有订单信息
     * @param orderInfo
     * @return 所有商品信息
     */
    List<OrderInfo> listOrdersByPage(OrderInfo orderInfo);

    /**
     * 查询登录店长的门店编号
     * @param userId
     * @return
     */
    OrderInfo findCode(@Param("userId") String userId);

    /**
     * 查询订单信息
     * @param orderCode 商品编码
     * @return 查询结果
     */
    List<OrderInfo> findOrderById(@Param("orderCode") String orderCode);

    /**
     * 修改订单状态
     * @param listCode 选中的订单编号集合
     * @param orderStatus
     * @param userId 更新人
     * @return 修改结果
     */
    int updateOrderStatus(@Param("listCode") List<String> listCode, @Param("orderStatus") int orderStatus, @Param("userId") String userId);
}
