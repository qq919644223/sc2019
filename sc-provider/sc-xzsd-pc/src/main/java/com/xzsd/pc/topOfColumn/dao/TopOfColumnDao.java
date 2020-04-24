package com.xzsd.pc.topOfColumn.dao;

import com.xzsd.pc.topOfColumn.entity.TopOfColumnInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TopOfColumnDao {
    /**
     * 查询顶部栏
     * @param userId 用户编号
     * @return 查询结果
     */
    TopOfColumnInfo getTopOfColumn(@Param("userId")String userId);

}
