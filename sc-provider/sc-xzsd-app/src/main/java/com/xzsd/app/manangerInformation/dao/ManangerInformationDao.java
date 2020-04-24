package com.xzsd.app.manangerInformation.dao;

import com.xzsd.app.manangerInformation.entity.ManangerInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ManangerInformationDao {
    /**
     * 查询当前登录店长所属的区
     * @param userId
     * @return
     */
    String findAreaId(@Param("userId")String userId);

    /**
     * 查询店长门下的司机信息
     * @param areaId
     * @return
     */
    List<ManangerInformation> listManangerDrivers(@Param("areaId") String areaId);
}
