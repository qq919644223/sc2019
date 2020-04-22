package com.xzsd.app.clientInformation.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientInformation.dao.ClientInformationDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description 我的（客户个人信息）管理
 * @author chenchaotao
 * @time 2020-04-22
 */
@Service
public class ClientInformationService {
    @Resource
    private ClientInformationDao clientInformationDao;
    /**
     * updateClientInvite 修改邀请码
     * @param inviteCode
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-22
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateClientInvite(String inviteCode){
        AppResponse appResponse = AppResponse.success("修改成功");
        //获取当前登录人id
        String userId = SecurityUtils.getCurrentUserId();
        //校验邀请码是否存在
        String countCode = clientInformationDao.findCode(inviteCode);
        if (countCode == null){
            return AppResponse.bizError("该邀请码不存在，请重新输入");
        }
        //修改邀请码信息
        int count = clientInformationDao.updateClientInvite(inviteCode,userId);
        if (0 == count){
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }
}
