package com.xzsd.pc.topOfColumn.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.topOfColumn.service.TopOfColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 顶部栏管理
 * @author chenchaotao
 * @time 2020-04-24
 */
@RestController
@RequestMapping("/topOfColumn")
public class TopOfColumnController {
    private static final Logger logger = LoggerFactory.getLogger(TopOfColumnController.class);
    @Resource
    private TopOfColumnService topOfColumnService;
    /**
     * getTopOfColumn 查询顶部栏
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-24
     */
    @RequestMapping(value = "getTopOfColumn")
    public AppResponse getTopOfColumn(){
        try{
            return topOfColumnService.getTopOfColumn();
        }catch (Exception e){
            logger.error("查询顶部栏错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
