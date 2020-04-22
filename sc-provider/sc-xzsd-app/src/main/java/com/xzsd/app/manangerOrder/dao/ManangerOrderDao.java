package com.xzsd.app.manangerOrder.dao;

import com.xzsd.app.manangerOrder.entity.ManangerOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManangerOrderDao {
    /**
     * 获取当前登录店长的用户编号查出其门店编号
     * @param userId
     * @return 门店编号
     */
    String findCode(@Param("userId") String userId);

    /**
     * 查询店长订单列表
     * @param manangerOrderInfo
     * @return 订单列表
     */
    List<ManangerOrderInfo> listManagerOrdersByPage(ManangerOrderInfo manangerOrderInfo);

    /**
     * 修改店长订单状态
     *  @param manangerOrderInfo
     * @return 修改结果
     */
    int updateManangerOrderState(ManangerOrderInfo manangerOrderInfo);
}
