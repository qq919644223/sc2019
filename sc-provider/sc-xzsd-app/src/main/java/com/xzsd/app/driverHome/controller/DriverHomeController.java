package com.xzsd.app.driverHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.driverHome.service.DriverHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 司机管理
 * @author chenchaotao
 * @time 2020-04-22
 */
@RestController
@RequestMapping("/driverHome")
public class DriverHomeController {
    private static final Logger logger = LoggerFactory.getLogger(DriverHomeController.class);
    @Resource
    private DriverHomeService driverHomeService;
    /**
     * listDriverStores 查询司机负责的门店信息
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-22
     */
    @RequestMapping(value = "listDriverStores")
    public AppResponse listDriverStores(){
        try{
            return driverHomeService.listDriverStores();
        }catch (Exception e){
            logger.error("查询司机负责的门店信息异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
