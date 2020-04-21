package com.xzsd.app.clientShopCart.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientShopCart.entity.ClientShopCartInfo;
import com.xzsd.app.clientShopCart.service.ClientShopCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 购物车管理
 * @author chenchaotao
 * @time 2020-04-14
 */
@RestController
@RequestMapping("/clientShopCart")
public class ClientShopCartController {
    private static final Logger logger = LoggerFactory.getLogger(ClientShopCartController.class);
    @Resource
    private ClientShopCartService clientShopCartService;

    /**
     * addShoppingCart 新增购物车
     * @param clientShopCartInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-14
     */
    @PostMapping("addShoppingCart")
    public AppResponse addShoppingCart(ClientShopCartInfo clientShopCartInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            clientShopCartInfo.setCreateBy(userId);
            AppResponse appResponse = clientShopCartService.addShoppingCart(clientShopCartInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("新增购物车商品失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listShoppingCarts 查询购物车列表
     * @param clientShopCartInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-14
     */
    @RequestMapping(value = "listShoppingCarts")
    public AppResponse listShoppingCarts(ClientShopCartInfo clientShopCartInfo){
        try{
            return clientShopCartService.listShoppingCarts(clientShopCartInfo);
        }catch (Exception e){
            logger.error("查询购物车列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateShoppingCart 修改购物车
     * @param clientShopCartInfo
     * @return AppResponse
     * @author chenchaotao
     * @Data 2020-04-14
     */
    @PostMapping("updateShoppingCart")
    public AppResponse updateShoppingCart(ClientShopCartInfo clientShopCartInfo){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            clientShopCartInfo.setLastModifiedBy(userId);
            return clientShopCartService.updateShoppingCart(clientShopCartInfo);
        }catch (Exception e){
            logger.error("修改购物车错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * deleteShoppingCart 删除商品
     * @param shopCartId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-14
     */
    @PostMapping("deleteShoppingCart")
    public AppResponse deleteShoppingCart(String shopCartId) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return clientShopCartService.deleteShoppingCart(shopCartId,userId);
        } catch (Exception e) {
            logger.error("购物车删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
