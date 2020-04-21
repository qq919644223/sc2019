package com.xzsd.app.register.dao;

import com.xzsd.app.register.entity.RegisterInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterDao {
    /**
     * 统计用户账号或手机号数量
     * @param registerInfo 用户信息
     * @return
     */
    int countUser(RegisterInfo registerInfo);

    /**
     * 注册用户
     * @param registerInfo 用户信息
     * @return
     */
    int clientRegister(RegisterInfo registerInfo);

    /**
     * 新增客户到客户表
     * @param registerInfo
     * @return
     */
    int addCustomer(RegisterInfo registerInfo);
}
