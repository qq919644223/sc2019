package com.xzsd.app.clientInformation.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Mapper
public interface ClientInformationDao {
    /**
     * 检验邀请码是否存在
     * @param inviteCode 邀请码
     * @return
     */
    String findCode(String inviteCode);
    /**
     * 修改邀请码
     * @param inviteCode 邀请码
     * @return 修改结果
     */
    int updateClientInvite(@Param("inviteCode") String inviteCode,@Param("userId") String userId);
}
