package com.xzsd.app.clientOrder.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.entity.GoodsEvaluate;
import com.xzsd.app.clientOrder.entity.OrderEvaluate;
import com.xzsd.app.clientOrder.service.ClientOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 订单管理
 * @author chenchaotao
 * @time 2020-04-14
 */
@RestController
@RequestMapping("/clientOrder")
public class ClientOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ClientOrderController.class);
    @Resource
    private ClientOrderService clientOrderService;
    /**
     * addOrder 新增订单
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-18
     */
    @PostMapping("addOrder")
    public AppResponse addOrder(String goodsId,String goodsPrice,String clientGoodsNum,String storeId) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            AppResponse appResponse = clientOrderService.addOrder(goodsId,goodsPrice,clientGoodsNum,storeId,userId);
            return appResponse;
        } catch (Exception e) {
            logger.error("订单新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listOrder 查询订单列表
     * @param clientOrderInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-21
     */
    @RequestMapping(value = "listOrder")
    public AppResponse listOrder(ClientOrderInfo clientOrderInfo){
        try{
            return clientOrderService.listOrder(clientOrderInfo);
        }catch (Exception e){
            logger.error("查询订单列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateOrderState 修改订单状态
     * @param clientOrderInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-22
     */
    @PostMapping("updateOrderState")
    public AppResponse updateOrderState(ClientOrderInfo clientOrderInfo){
        try{
            return clientOrderService.updateOrderState(clientOrderInfo);
        }catch (Exception e){
            logger.error("修改订单状态错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listOrderDeepen 查询订单详情
     * @param orderId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-22
     */
    @RequestMapping(value = "listOrderDeepen")
    public AppResponse listOrderDeepen(String orderId){
        try{
            return clientOrderService.listOrderDeepen(orderId);
        }catch (Exception e){
            logger.error("订单查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listGoodsForEvaluate 查询订单评价商品信息列表
     * @param orderId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-22
     */
    @RequestMapping(value = "listGoodsForEvaluate")
    public AppResponse listGoodsForEvaluate(String orderId){
        try{
            return clientOrderService.listGoodsForEvaluate(orderId);
        }catch (Exception e){
            logger.error("订单评价商品信息查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * addGoodsEvaluate 新增订单商品评价
     * @param order
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-23
     */
    @PostMapping("addGoodsEvaluate")
    public AppResponse addGoodsEvaluate(@RequestBody JSONObject order) {
        try {
            JSONObject orderEvaluateJson = order.getJSONObject("orderEvaluate");
            OrderEvaluate orderEvaluate = (OrderEvaluate) JSONObject.toJavaObject(orderEvaluateJson,OrderEvaluate.class);
            String orderId = orderEvaluate.getOrderId();
            JSONArray goodsEvaluateJson = orderEvaluateJson.getJSONArray("evaluateList");
            List<GoodsEvaluate> goodsEvaluateList = new ArrayList<GoodsEvaluate>();
            for (int i = 0; i < goodsEvaluateJson.size(); i++) {
                String evaluateCode = StringUtil.getCommonCode(2);
                JSONObject goodsJson = (JSONObject) goodsEvaluateJson.getJSONObject(i);
                GoodsEvaluate goodsEvaluate = (GoodsEvaluate)JSONObject.toJavaObject(goodsJson,GoodsEvaluate.class);
               goodsEvaluate.setEvaluateCode(evaluateCode);
               goodsEvaluateList.add(i,goodsEvaluate);
            }
            AppResponse appResponse = clientOrderService.addGoodsEvaluate(goodsEvaluateList,orderId);
            return appResponse;
        } catch (Exception e) {
            logger.error("新增订单商品评价失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
