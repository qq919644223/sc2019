package com.xzsd.pc.store.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.store.entity.StoreInfo;
import com.xzsd.pc.store.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 门店信息管理
 * @author chenchaotao
 * @time 2020-04-10
 */

@RestController
@RequestMapping("/store")
public class StoreController {
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Resource
    private StoreService storeService;

    /**
     * listStore 门店信息列表
     * @param
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-10
     */
    @RequestMapping(value = "listStore")
    public AppResponse listStore(StoreInfo storeInfo){
        try{
            return storeService.listStore(storeInfo);
        }catch (Exception e){
            logger.error("查询门店信息列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * addStore 门店信息新增
     * @param  storeInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-10
     */
    @PostMapping("addStore")
    public AppResponse addStore(StoreInfo storeInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            storeInfo.setCreateBy(userId);
            AppResponse appResponse = storeService.addStore(storeInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("门店信息新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * findStoreById 查询门店详情
     * @param storeId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-11
     */
    @RequestMapping(value = "findStoreById")
    public AppResponse findStoreById(String storeId){
        try{
            return storeService.findStoreById(storeId);
        }catch (Exception e){
            logger.error("门店详情查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateStoreById 修改门店信息
     * @param storeInfo
     * @return AppResponse
     * @author chenchaotao
     * @Data 2020-04-11
     */
    @PostMapping("updateStoreById")
    public AppResponse updateStoreById(StoreInfo storeInfo){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            storeInfo.setLastModifiedBy(userId);
            return storeService.updateStoreById(storeInfo);
        }catch (Exception e){
            logger.error("修改门店信息错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * deleteStore 删除门店
     * @param storeId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-11
     */
    @PostMapping("deleteStore")
    public AppResponse deleteStore(String storeId){
        try{
            //获取用户id
            String Id = SecurityUtils.getCurrentUserId();
            return storeService.deleteStore(storeId,Id);
        }catch (Exception e){
            logger.error("门店删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }


    /**
     * listArea 省市区列表下拉查询
     * @param cateId
     * @return AppResponse
     * @author chenchaotao
     * @date 2020-04-11
     */
    @PostMapping("listArea")
    public AppResponse listArea(String cateId){
        try{
            return storeService.listArea(cateId);
        }catch (Exception e){
            logger.error("查询区列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }


}
