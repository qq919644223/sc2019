package com.xzsd.app.register.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.register.entity.RegisterInfo;
import com.xzsd.app.register.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 注册
 * @author chenchaotao
 * @time 2020-04-13
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Resource
    private RegisterService registerService;
    /**
     * clientRegister 新增用户
     * @param registerInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-13
     */
    @PostMapping("clientRegister")
    public AppResponse clientRegister(RegisterInfo registerInfo) {
        try {
            AppResponse appResponse = registerService.clientRegister(registerInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("用户注册失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
