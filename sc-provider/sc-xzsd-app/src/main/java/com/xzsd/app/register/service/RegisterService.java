package com.xzsd.app.register.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.clientInformation.dao.ClientInformationDao;
import com.xzsd.app.register.dao.RegisterDao;
import com.xzsd.app.register.entity.RegisterInfo;
import com.xzsd.app.util.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
/**
 * @Description 注册管理实现类
 * @Author chenchaotao
 * @Date 2020-04-13
 */
@Service
public class RegisterService {
    @Resource
    private RegisterDao registerDao;
    @Resource
    private ClientInformationDao clientInformationDao;
    /**
     * clientRegister 注册用户
     * @param registerInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-13
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse clientRegister(RegisterInfo registerInfo) {
        //校验邀请码是否存在
        String countCode = clientInformationDao.findCode(registerInfo.getInviteCode());
        if (countCode == null){
            return AppResponse.bizError("该邀请码不存在，请重新输入");
        }
        // 校验账号是否存在
        int countUser = registerDao.countUser(registerInfo);
        if(0 != countUser) {
            return AppResponse.bizError("用户账号,手机号或身份证号已存在，请重新输入！");
        }
        //密码加密
        String pwd = PasswordUtils.generatePassword(registerInfo.getUserPassword());
        String userId = StringUtil.getCommonCode(2);
        registerInfo.setCustomerId(StringUtil.getCommonCode(2));
        registerInfo.setUserId(userId);
        registerInfo.setUserPassword(pwd);
        registerInfo.setCreateBy(userId);
        registerInfo.setIsDeleted(0);
        registerInfo.setLastModifiedBy(userId);
        // 新增用户
        int count = registerDao.clientRegister(registerInfo);
        // 新增到客户表
        int countCustomer = registerDao.addCustomer(registerInfo);
        if(0 == count || 0 == countCustomer) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

}
