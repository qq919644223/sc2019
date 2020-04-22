package com.xzsd.app.driverHome.dao;

import com.xzsd.app.driverHome.entity.DriverHomeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DriverHomeDao {
    /**
     * 获取当前登录司机的用户编号查出司机所在区域
     * @param userId
     * @return
     */
    String findAreaId(@Param("userId")String userId);

    /**
     * 查询司机负责的门店信息
     * @param areaId
     * @return
     */
    List<DriverHomeInfo> listDriverStores(@Param("areaId") String areaId);

}
