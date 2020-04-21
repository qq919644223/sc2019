package com.xzsd.app.clientGoods.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientGoods.entity.GoodsEvaluates;
import com.xzsd.app.clientGoods.service.ClientGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 商品管理
 * @author chenchaotao
 * @time 2020-04-13
 */
@RestController
@RequestMapping("/clientGoods")
public class ClientGoodsController {
    private static final Logger logger = LoggerFactory.getLogger(ClientGoodsController.class);
    @Resource
    private ClientGoodsService clientGoodsService;

    /**
     * getGoods 查询商品详情
     * @param goodsId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-13
     */
    @RequestMapping(value = "getGoods")
    public AppResponse getGoods(String goodsId){
        try{
            return clientGoodsService.getGoods(goodsId);
        }catch (Exception e){
            logger.error("商品查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listGoodsEvaluates 查询商品评价列表
     * @param goodsEvaluates
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-18
     */
    @RequestMapping(value = "listGoodsEvaluates")
    public AppResponse listGoodsEvaluates(GoodsEvaluates goodsEvaluates){
        try{
            return clientGoodsService.listGoodsEvaluates(goodsEvaluates);
        }catch (Exception e){
            logger.error("查询商品评价列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listOneGoodsClassify 查询一级商品分类列表
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-13
     */
    @RequestMapping(value = "listOneGoodsClassify")
    public AppResponse listOneGoodsClassify(){
        try{
            return clientGoodsService.listOneGoodsClassify();
        }catch (Exception e){
            logger.error("查询一级商品分类列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listGetClassGoods 查询二级商品分类以及商品
     * @param classifyId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-14
     */
    @PostMapping("/listGetClassGoods")
    public AppResponse listCategory(String classifyId){
        try{
            return clientGoodsService.listGetClassGoods(classifyId);
        }catch (Exception e){
            logger.error("查询二级商品分类以及商品失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
