package com.xzsd.pc.menu.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.menu.entity.MenuInfo;
import com.xzsd.pc.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Description 菜单管理
 * @Author chenchaotao
 * @Date 2020-04-12
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Resource
    private MenuService menuService;

    /**
     * listMenus 菜单列表
     * @return AppReesponse
     * @author chenchaotao
     * @Date 2020-04-12
     */
    @RequestMapping(value = "listMenus")
    public AppResponse listMenus(){
        try{
            return menuService.listMenus();
        }catch (Exception e){
            logger.error("查询菜单列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listMenuHome 根据角色查询首页菜单列表
     * @return AppReesponse
     * @author chenchaotao
     * @Date 2020-04-12
     */
    @RequestMapping(value = "listMenuHome")
    public AppResponse listMenuHome(){
        try{
            return menuService.listMenus();
        }catch (Exception e){
            logger.error("查询菜单列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * findMenuById 查询菜单详情
     * @param menuId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-12
     */
    @RequestMapping(value = "findMenuById")
    public AppResponse findMenuById(String menuId){
        try{
            return menuService.findMenuById(menuId);
        }catch (Exception e){
            logger.error("菜单查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * addMenu 新增用户
     * @param menuInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-12
     */
    @PostMapping("addMenu")
    public AppResponse addMenu(MenuInfo menuInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            menuInfo.setCreateBy(userId);
            AppResponse appResponse = menuService.addMenu(menuInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("菜单新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateMenuById 修改菜单信息
     * @param menuInfo
     * @return AppResponse
     * @author chenchaotoa
     * @Date 2020-04-12
     */
    @PostMapping("updateMenuById")
    public AppResponse updateMenuById(MenuInfo menuInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            menuInfo.setLastModifiedBy(userId);
            return menuService.updateMenuById(menuInfo);
        }catch (Exception e){
            logger.error("修改菜单信息错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * deleteMenu 删除菜单
     * @param menuId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-12
     */
    @PostMapping("deleteMenu")
    public AppResponse deleteMenu(String menuId){
        try{
            //获取用户id
            String Id = SecurityUtils.getCurrentUserId();
            return menuService.deleteMenu(menuId,Id);
        }catch (Exception e){
            logger.error("菜单删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
