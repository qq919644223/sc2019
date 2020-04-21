package com.xzsd.pc.customer.dao;


import com.xzsd.pc.customer.entity.CustomerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

@Mapper
public interface CustomerDao {
    /**
     * 获取所有客户信息
     * @param customerInfo
     * @return 所有客户信息
     */
    List<CustomerInfo> listCustomersByPage(CustomerInfo customerInfo);

    /**
     * 获取当前登录的角色编号
     * @param userId
     * @return 角色编号
     */
    int getUserRole(@Param("userId") String userId);

    /**
     * 通过用户编号找到邀请码
     * @param userId
     * @return 邀请码信息
     */
    CustomerInfo findCode(@Param("userId") String userId);
}
