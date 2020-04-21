package com.xzsd.pc.order.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.order.entity.OrderInfo;
import com.xzsd.pc.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 订单管理
 * @author chenchaotao
 * @time 2020-04-05
 */

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Resource
    private OrderService orderService;
    /**
     * listOrders 订单列表
     * @param orderInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-05
     */
    @RequestMapping(value = "listOrders")
    public AppResponse listOrders(OrderInfo orderInfo){
        try{
            return orderService.listOrders(orderInfo);
        }catch (Exception e){
            logger.error("查询订单列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * findOrderById 查询订单详情
     * @param orderCode
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-05
     */
    @RequestMapping(value = "findOrderById")
    public AppResponse findOrderById(String orderCode){
        try{
            return orderService.findOrderById(orderCode);
        }catch (Exception e){
            logger.error("订单查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateOrderStatus 修改订单状态
     * @param orderCode
     * @param orderStatus
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-26
     */
    @PostMapping("updateOrderStatus")
    public AppResponse updateOrderStatus(String orderCode,int orderStatus){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return orderService.updateOrderStatus(orderCode,orderStatus,userId);
        }catch (Exception e){
            logger.error("修改订单状态错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
