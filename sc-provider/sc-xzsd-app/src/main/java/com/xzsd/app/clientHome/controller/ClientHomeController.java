package com.xzsd.app.clientHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientHome.entity.ClientHomeInfo;
import com.xzsd.app.clientHome.service.ClientHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 客户端首页管理
 * @author chenchaotao
 * @time 2020-04-13
 */
@RestController
@RequestMapping("/clientHome")
public class ClientHomeController {
    private static final Logger logger = LoggerFactory.getLogger(ClientHomeController.class);
    @Resource
    private ClientHomeService clientHomeService;

    /**
     * listRotationCharHome 查询首页轮播图
     * @param clientHomeInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-13
     */
    @RequestMapping(value = "listRotationCharHome")
    public AppResponse listRotationCharHome(ClientHomeInfo clientHomeInfo){
        try{
            return clientHomeService.listRotationCharHome(clientHomeInfo);
        }catch (Exception e){
            logger.error("查询首页轮播图异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listHotGoods 查询热门商品
     * @param clientHomeInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-13
     */
    @RequestMapping(value = "listHotGoods")
    public AppResponse listHotGoods(ClientHomeInfo clientHomeInfo){
        try{
            return clientHomeService.listHotGoods(clientHomeInfo);
        }catch (Exception e){
            logger.error("查询热门商品异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
