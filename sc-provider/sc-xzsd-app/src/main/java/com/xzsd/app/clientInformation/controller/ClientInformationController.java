package com.xzsd.app.clientInformation.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientInformation.service.ClientInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 我的（客户个人信息）管理
 * @author chenchaotao
 * @time 2020-04-22
 */
@RestController
@RequestMapping("clientInformation")
public class ClientInformationController {
    private static final Logger logger = LoggerFactory.getLogger(ClientInformationController.class);
    @Resource
    private ClientInformationService clientInformationService;
    /**
     * updateClientInvite 修改邀请码
     * @param inviteCode
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-22
     */
    @PostMapping("updateClientInvite")
    public AppResponse updateClientInvite(String inviteCode) {
        try {
            return clientInformationService.updateClientInvite(inviteCode);
        } catch (Exception e) {
            logger.error("修改邀请码失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
