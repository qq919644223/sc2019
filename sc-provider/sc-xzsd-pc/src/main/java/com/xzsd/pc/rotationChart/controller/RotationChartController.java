package com.xzsd.pc.rotationChart.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.AuthUtils;
import com.xzsd.pc.rotationChart.entity.RotationChartInfo;
import com.xzsd.pc.rotationChart.service.RotationChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 轮播图管理
 * @Author chenchaotao
 * @Date 2020-03-27
 */
@RestController
@RequestMapping("/rotationChart")
public class RotationChartController {
    private static final Logger logger = LoggerFactory.getLogger(RotationChartController.class);
    @Resource
    private RotationChartService rotationChartService;
    /**
     * addCharts 轮播图新增
     * @param rotationChartInfo
     * @ruturn AppResponse
     * @author chenchaotao
     * @Date 2020-03-27
     */
        @PostMapping("addCharts")
    public AppResponse addCharts(RotationChartInfo rotationChartInfo){
        try {
             //获取用户id
             String userId = SecurityUtils.getCurrentUserId();
             rotationChartInfo.setCreateBy(userId);
             AppResponse appResponse = rotationChartService.addCharts(rotationChartInfo);
             return appResponse;
        }catch (Exception e){
            logger.error("轮播图新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * deleteChart 轮播图删除
     * @param chartId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-27
     */
    @PostMapping("deleteChart")
    public AppResponse deleteChart(String chartId){
          try{
              String userId = SecurityUtils.getCurrentUserId();
              return rotationChartService.deleteChart(chartId,userId);
          }catch (Exception e){
              logger.error("轮播图删除失败",e);
              System.out.println(e.toString());
              throw e;
          }
    }

    /**
     * listCharts 轮播图列表查询
     * @param rotationChartInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-28
     */
    @PostMapping("listCharts")
    public AppResponse listCharts(RotationChartInfo rotationChartInfo){
        try {
             return rotationChartService.listCharts(rotationChartInfo);
        }catch (Exception e){
            logger.error("轮播图列表查询失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updatePictureStatus 修改轮播图状态
     * @param chartId
     * @param pictureStatus
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-28
     */
    @PostMapping("updatePictureStatus")
    public AppResponse updatePictureStatus(String chartId,int pictureStatus){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            return rotationChartService.updatePictureStatus(chartId,pictureStatus,userId);
        }catch (Exception e){
            logger.error("轮播图状态修改失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listSelectGoods 商品选择列表查询
     * @param rotationChartInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-28
     */
    @PostMapping("listSelectGoods")
    public AppResponse listSelectGoods(RotationChartInfo rotationChartInfo){
        try {
            return rotationChartService.listSelectGoods(rotationChartInfo);
        }catch (Exception e){
            logger.error("商品列表查询失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
