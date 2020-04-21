package com.xzsd.pc.user.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.user.entity.UserInfo;
import com.xzsd.pc.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 用户管理
 * @Author chenchaotao
 * @Date 2020-03-24
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;
    /**
     * addUser 新增用户
     * @param userInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-24
     */
    @PostMapping("addUser")
    public AppResponse saveUser(UserInfo userInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setCreateBy(userId);
            AppResponse appResponse = userService.addUser(userInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("用户新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * deleteUser 删除用户
     * @param userId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-24
     */
    @PostMapping("deleteUser")
    public AppResponse deleteUser(String userId){
        try{
            //获取用户id
            String Id = SecurityUtils.getCurrentUserId();
            return userService.deleteUser(userId,Id);
        }catch (Exception e){
            logger.error("用户删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateUserById 修改用户信息
     * @param userInfo
     * @return AppResponse
     * @author chenchaotoa
     * @Date 2020-03-06
     */
    @PostMapping("updateUserById")
    public AppResponse updateUserById(UserInfo userInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setLastModifiedBy(userId);
            return userService.updateUserById(userInfo);
        }catch (Exception e){
            logger.error("修改用户信息错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * listUsers 用户列表（分页）
     * @param userInfo
     * @return AppReesponse
     * @author chenchaotao
     * @Date 2020-03-26
     */
    @RequestMapping(value = "listUsers")
    public AppResponse listUsers(UserInfo userInfo){
        try{
            return userService.listUsers(userInfo);
        }catch (Exception e){
            logger.error("查询用户列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * findUserById 查询用户详情
     * @param userId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-26
     */
    @RequestMapping(value = "findUserById")
    public AppResponse findUserById(String userId){
        try{
            return userService.findUserById(userId);
        }catch (Exception e){
            logger.error("用户查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
