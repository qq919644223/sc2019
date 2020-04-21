package com.xzsd.pc.goods.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.AuthUtils;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description商品管理
 * @author chenchaotao
 * @time 2020-03-24
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Resource
   private GoodsService goodsService;
    /**
     * addGoods 新增商品
     * @param goodsInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-25
     */
    @PostMapping("addGoods")
    public AppResponse saveUser(GoodsInfo goodsInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            goodsInfo.setCreateBy(userId);
            AppResponse appResponse = goodsService.addGoods(goodsInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * deleteGoods 删除商品
     * @param goodsCode
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-25
     */
    @PostMapping("deleteGoods")
    public AppResponse deleteGoods(String goodsCode) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return goodsService.deleteGoods(goodsCode,userId);
        } catch (Exception e) {
            logger.error("商品删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateGoodsById 修改商品信息
     * @param goodsInfo
     * @return AppResponse
     * @author chenchaotao
     * @Data 2020-03-25
     */
    @PostMapping("updateGoodsById")
    public AppResponse updateGoodsById(GoodsInfo goodsInfo){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            goodsInfo.setLastModifiedBy(userId);
            return goodsService.updateGoodsById(goodsInfo);
        }catch (Exception e){
            logger.error("修改商品信息错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listGoods 商品列表
     * @param goodsInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-25
     */
     @RequestMapping(value = "listGoods")
    public AppResponse listGoods(GoodsInfo goodsInfo){
         try{
             return goodsService.listGoods(goodsInfo);
         }catch (Exception e){
             logger.error("查询商品列表异常",e);
             System.out.println(e.toString());
             throw e;
         }
     }

    /**
     * findGoodsById 查询商品详情
     * @param goodsCode
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-25
     */
    @RequestMapping(value = "findGoodsById")
    public AppResponse findGoodsById(String goodsCode){
        try{
            return goodsService.findGoodsById(goodsCode);
        }catch (Exception e){
            logger.error("商品查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateGoodsStatus 修改商品状态
     * @param goodsCode
     * @param goodsStatus
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-26
     */
    @PostMapping("updateGoodsStatus")
    public AppResponse updateGoodsStatus(String goodsCode,int goodsStatus){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return goodsService.updateGoodsStatus(goodsCode,goodsStatus,userId);
        }catch (Exception e){
            logger.error("修改商品状态错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listGoodsClassify 查询商品分类下拉框
     * @param classifyId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-13
     */
    @RequestMapping(value = "listGoodsClassify")
    public AppResponse listGoodsClassify(String classifyId){
        try{
            return goodsService.listGoodsClassify(classifyId);
        }catch (Exception e){
            logger.error("查询商品分类列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
