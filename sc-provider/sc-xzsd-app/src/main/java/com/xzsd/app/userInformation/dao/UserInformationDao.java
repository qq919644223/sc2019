package com.xzsd.app.userInformation.dao;

import com.xzsd.app.userInformation.entity.UserInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserInformationDao {
    /**
     * 获取当前登录的角色编号
     * @param userId
     * @return 角色编号
     */
    String getUserRole(@Param("userId") String userId);

    /**
     * 查询个人信息
     * @param userId 用户编号
     * @return 查询结果
     */
    UserInformation getUser(@Param("userId")String userId);

    /**
     * 查询用户详情
     * @param userId 用户编号
     * @return 查询结果
     */
    UserInformation findUserById(@Param("userId")String userId);

    /**
     * 修改密码
     * @param userInformation 用户信息
     * @return
     */
    int updateUserPassword(UserInformation userInformation);
}
