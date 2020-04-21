package com.xzsd.pc.rotationChart.dao;


import com.xzsd.pc.rotationChart.entity.RotationChartInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface RotationChartDao {
    /**
     * 统计商品编码数量
     * @param rotationChartInfo 轮播图信息
     * @return
     */
    int countGoodsSortId(RotationChartInfo rotationChartInfo);

    /**
     * 统计排序号数量
     * @param rotationChartInfo 轮播图信息
     * @return
     */
    int countSortId(RotationChartInfo rotationChartInfo);

    /**
     * 新增轮播图信息
     * @param rotationChartInfo
     * @return
     */
    int addCharts(RotationChartInfo rotationChartInfo);

    /**
     * 删除轮播图信息
     * @param listId 选中的轮播图编号集合
     * @param userId 更新人
     * @return
     */
    int deleteChart(@Param("listId") List<String> listId, @Param("userId") String userId);

    /**
     * 查询轮播图列表信息
     * @param rotationChartInfo
     * @return 所有轮播图信息
     */
    List<RotationChartInfo> listChartsByPage(RotationChartInfo rotationChartInfo);

    /**
     * 修改轮播图状态
     * @param listId
     * @param pictureStatus
     * @param userId
     * @return 修改结果
     */
    int updatePictureStatus(@Param("listId") List<String> listId,@Param("pictureStatus") int pictureStatus,@Param("userId")String userId);

    /**
     * 查询商品列表信息
     * @param rotationChartInfo
     * @return 所有商品信息
     */
    List<RotationChartInfo> listSelectGoodsByPage(RotationChartInfo rotationChartInfo);
}
