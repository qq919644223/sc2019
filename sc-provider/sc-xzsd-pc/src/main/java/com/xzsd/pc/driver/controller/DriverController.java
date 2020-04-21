package com.xzsd.pc.driver.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.driver.entity.DriverInfo;
import com.xzsd.pc.driver.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 司机信息管理
 * @Author chenchaotao
 * @Date 2020-04-11
 */
@RestController
@RequestMapping("/driver")
public class DriverController {
    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);
    @Resource
    private DriverService driverService;

    /**
     * listDriver 司机列表（分页）
     * @param driverInfo
     * @return AppReesponse
     * @author chenchaotao
     * @Date 2020-04-11
     */
    @RequestMapping(value = "listDriver")
    public AppResponse listDriver(DriverInfo driverInfo){
        try{
            return driverService.listDriver(driverInfo);
        }catch (Exception e){
            logger.error("查询司机列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * addDriver 新增司机
     * @param driverInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-11
     */
    @PostMapping("addDriver")
    public AppResponse addDriver(DriverInfo driverInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            driverInfo.setCreateBy(userId);
            AppResponse appResponse = driverService.addDriver(driverInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("司机新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * findDriverById 查询司机详情
     * @param driverId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-11
     */
    @RequestMapping(value = "findDriverById")
    public AppResponse findDriverById(String driverId){
        try{
            return driverService.findDriverById(driverId);
        }catch (Exception e){
            logger.error("司机查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateDriverById 修改司机信息
     * @param driverInfo
     * @return AppResponse
     * @author chenchaotoa
     * @Date 2020-04-11
     */
    @PostMapping("updateDriverById")
    public AppResponse updateUserById(DriverInfo driverInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            driverInfo.setLastModifiedBy(userId);
            return driverService.updateDriverById(driverInfo);
        }catch (Exception e){
            logger.error("修改司机信息错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * deleteDriver 删除司机
     * @param driverId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-12
     */
    @PostMapping("deleteDriver")
    public AppResponse deleteDriver(String driverId){
        try{
            //获取用户id
            String Id = SecurityUtils.getCurrentUserId();
            return driverService.deleteDriver(driverId,Id);
        }catch (Exception e){
            logger.error("司机删除错误", e);
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
            return driverService.listArea(cateId);
        }catch (Exception e){
            logger.error("查询区列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
