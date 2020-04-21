package com.xzsd.app.userInformation.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.userInformation.entity.UserInformation;
import com.xzsd.app.userInformation.service.UserInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 用户信息管理
 * @Author chenchaotao
 * @Date 2020-04-14
 */
@RestController
@RequestMapping("/userInformation")
public class UserInformationController {
    private static final Logger logger = LoggerFactory.getLogger(UserInformationController.class);
    @Resource
    private UserInformationService userInformationService;
    /**
     * getUser 查询个人信息
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-14
     */
    @RequestMapping(value = "getUser")
    public AppResponse getUser(){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            return userInformationService.getUser(userId);
        }catch (Exception e){
            logger.error("个人信息查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * updateUserPassword 修改密码
     * @param userInformation
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-14
     */
    @PostMapping("updateUserPassword")
    public AppResponse updateUserPassword(UserInformation userInformation) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInformation.setLastModifiedBy(userId);
            return userInformationService.updateUserPassword(userInformation);
        } catch (Exception e) {
            logger.error("修改密码失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
