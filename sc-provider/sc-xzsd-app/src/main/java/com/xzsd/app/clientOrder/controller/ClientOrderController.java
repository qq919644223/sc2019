package com.xzsd.app.clientOrder.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.service.ClientOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
