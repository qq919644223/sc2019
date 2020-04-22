package com.xzsd.app.manangerInformation.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.manangerInformation.service.ManangerInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 店长个人信息
 * @author chenchaotao
 * @time 2020-04-22
 */
@RestController
@RequestMapping("/manangerInformation")
public class ManangerInformationController {
    private static final Logger logger = LoggerFactory.getLogger(ManangerInformationController.class);
    @Resource
    private ManangerInformationService manangerInformationService;
    /**
     * listManangerDrivers 查询店长门下的司机信息
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-22
     */
    @RequestMapping(value = "listManangerDrivers")
    public AppResponse listManangerDrivers(){
        try{
            return manangerInformationService.listManangerDrivers();
        }catch (Exception e){
            logger.error("查询店长门下的司机信息异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
