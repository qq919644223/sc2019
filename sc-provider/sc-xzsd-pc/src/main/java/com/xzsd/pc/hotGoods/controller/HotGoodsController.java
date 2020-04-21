package com.xzsd.pc.hotGoods.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import com.xzsd.pc.hotGoods.service.HotGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 热门位商品管理
 * @author chenchaotao
 * @time 2020-04-05
 */

@RestController
@RequestMapping("/hotGoods")
public class HotGoodsController {
    private static final Logger logger = LoggerFactory.getLogger(HotGoodsController.class);
    @Resource
    private HotGoodsService hotGoodsService;
    /**
     * listHotGoods 热门位商品列表查询
     * @param hotGoodsInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-05
     */
    @RequestMapping(value = "listHotGoods")
    public AppResponse listHotGoods(HotGoodsInfo hotGoodsInfo){
        try{
            return hotGoodsService.listHotGoods(hotGoodsInfo);
        }catch (Exception e){
            logger.error("查询商品列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * addHotGoods 热门位商品新增
     * @param hotGoodsInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-05
     */
    @PostMapping("addHotGoods")
    public AppResponse addHotGoods(HotGoodsInfo hotGoodsInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            hotGoodsInfo.setCreateBy(userId);
            AppResponse appResponse = hotGoodsService.addHotGoods(hotGoodsInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("热门位商品新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateHotGoodsById 修改热门位商品信息
     * @param hotGoodsInfo
     * @return AppResponse
     * @author chenchaotao
     * @Data 2020-04-05
     */
    @PostMapping("updateHotGoodsById")
    public AppResponse updateHotGoodsById(HotGoodsInfo hotGoodsInfo){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            hotGoodsInfo.setLastModifiedBy(userId);
            return hotGoodsService.updateHotGoodsById(hotGoodsInfo);
        }catch (Exception e){
            logger.error("修改热门位商品信息错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * deleteHotGoods 删除热门位商品
     * @param hotGoodsId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-06
     */
    @PostMapping("deleteHotGoods")
    public AppResponse deleteHotGoods(String hotGoodsId) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return hotGoodsService.deleteHotGoods(hotGoodsId,userId);
        } catch (Exception e) {
            logger.error("热门位商品删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * findhotGoodsById 查询热门位商品详情
     * @param hotGoodsId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-06
     */
    @RequestMapping(value = "findhotGoodsById")
    public AppResponse findhotGoodsById(String hotGoodsId){
        try{
            return hotGoodsService.findhotGoodsById(hotGoodsId);
        }catch (Exception e){
            logger.error("热门位商品查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listSelectGoods 商品选择列表查询
     * @param hotGoodsInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-06
     */
    @PostMapping("listSelectGoods")
    public AppResponse listSelectGoods(HotGoodsInfo hotGoodsInfo){
        try {
            return hotGoodsService.listSelectGoods(hotGoodsInfo);
        }catch (Exception e){
            logger.error("商品列表查询失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * findHotGoodsNum 热门位商品展示数量查询
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-05
     */
    @PostMapping("findHotGoodsNum")
    public AppResponse findHotGoodsNum() {
        try {
            AppResponse appResponse = hotGoodsService.findHotGoodsNum();
            return appResponse;
        } catch (Exception e) {
            logger.error("热门位商品展示数量查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateHotGoodsNumber 修改热门位商品信息
     * @param hotGoodsNum
     * @return AppResponse
     * @author chenchaotao
     * @Data 2020-04-12
     */
    @PostMapping("updateHotGoodsNumber")
    public AppResponse updateHotGoodsById(String hotGoodsNum){
        try{
            return hotGoodsService.updateHotGoodsNumber(hotGoodsNum);
        }catch (Exception e){
            logger.error("修改热门位商品展示数量错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
