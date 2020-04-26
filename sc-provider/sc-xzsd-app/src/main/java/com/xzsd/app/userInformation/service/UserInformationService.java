package com.xzsd.app.userInformation.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.userInformation.dao.UserInformationDao;
import com.xzsd.app.userInformation.entity.UserInformation;
import com.xzsd.app.util.PasswordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 用户信息管理实现类
 * @Author chenchaotao
 * @Date 2020-04-14
 */
@Service
public class UserInformationService {
   @Resource
   private UserInformationDao userInformationDao;

    /**
     * getUser 查询个人信息
     * @param userId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-14
     */
    public AppResponse getUser(String userId){
//查询当前登录的角色
        String role = userInformationDao.getUserRole(userId);
        UserInformation userInformation = userInformationDao.getUser(userId);
        return AppResponse.success("查询成功！",userInformation);
    }

    /**
     * updateUserPassword 修改密码
     * @param userInformation
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-14
     */
    public AppResponse updateUserPassword(UserInformation userInformation) {
        AppResponse appResponse = AppResponse.success("修改密码成功！");
        //获取角色
        String userId = SecurityUtils.getCurrentUserId();
        userInformation.setUserId(userId);
        String role = userInformationDao.getUserRole(userId);
        userInformation.setRole(role);
        // 需要校验原密码是否正确
        if(null != userInformation.getUserPassword() && !"".equals(userInformation.getUserPassword())) {
            String newPwd = userInformation.getUserPassword();
            // 获取用户信息
            UserInformation userDetail = userInformationDao.findUserById(userId);
            if(null == userDetail) {
                return AppResponse.bizError("用户不存在或已被删除！");
            } else {
                if(!PasswordUtils.passwordMatch(newPwd,userDetail.getUserPassword())) {
                    return AppResponse.bizError("原密码不匹配，请重新输入！");
                }
            }
        }
        // 修改密码
        userInformation.setUserNewPassword(PasswordUtils.generatePassword(userInformation.getUserNewPassword()));
        int count = userInformationDao.updateUserPassword(userInformation);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改密码失败，请重试！");
        }
        return appResponse;
    }
}
