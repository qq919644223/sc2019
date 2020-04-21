package com.xzsd.pc.driver.dao;

import com.xzsd.pc.driver.entity.DriverInfo;
import com.xzsd.pc.store.entity.AreaInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DriverDao {
    /**
     * 获取所有司机信息
     * @param driverInfo 司机信息
     * @return 所有司机信息
     */
    List<DriverInfo> listDriverByPage(DriverInfo driverInfo);

    /**
     * 统计用户账号数量
     * @param driverInfo 司机信息
     * @return
     */
    int count(DriverInfo driverInfo);

    /**
     * 新增司机
     * @param driverInfo 司机信息
     * @return
     */
    int addDriver(DriverInfo driverInfo);

    /**
     * 新增用户
     * @param driverInfo 用户信息
     * @return
     */
    int addUser(DriverInfo driverInfo);

    /**
     * 查询司机详情
     * @param driverId 司机编号
     * @return 查询结果
     */
    DriverInfo findDriverById(@Param("driverId") String driverId);

    /**
     * 修改司机信息
     * @param driverInfo 司机信息
     * @return 修改结果
     */
    int updateDriverById(DriverInfo driverInfo);

    /**
     * 修改用户信息
     * @param driverInfo 司机信息
     * @return 修改结果
     */
    int updateUserById(DriverInfo driverInfo);

    /**
     * 删除用户
     * @param listCode 选中的用户编号集合
     * @param Id 更新人
     * @return
     */
    int deleteUser(@Param("listCode") List<String> listCode, @Param("Id") String Id);

    /**
     * 删除用户
     * @param listCode 选中的用户编号集合
     * @param Id 更新人
     * @return
     */
    int deleteDriver(@Param("listCode") List<String> listCode, @Param("Id") String Id);

    /**
     * 查询省市区下拉列表
     * @param cateId
     * @return 省市区列表信息
     */
    List<AreaInfo> listArea(@Param("cateId") String cateId);

}
