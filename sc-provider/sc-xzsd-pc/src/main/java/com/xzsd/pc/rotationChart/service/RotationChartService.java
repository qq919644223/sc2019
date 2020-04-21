package com.xzsd.pc.rotationChart.service;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.rotationChart.dao.RotationChartDao;
import com.xzsd.pc.rotationChart.entity.RotationChartInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * @DescriptionCharts 轮播图管理实现类
 * @author chenchaotao
 * @Date 2020-03-27
 */
@Service
public class RotationChartService {
    @Resource
    private RotationChartDao rotationChartDao;
    /**
     * addCharts 新增轮播图
     * @param rotationChartInfo
     * @return
     * @author chenchaotao
     * @Date 2020-03-27
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addCharts(RotationChartInfo rotationChartInfo){
        // 校验商品编号或排序号是否已存在
        int countGoodsSortId  = rotationChartDao.countGoodsSortId(rotationChartInfo);
        if(0 != countGoodsSortId) {
            return AppResponse.bizError("商品编号或排序号已存在，请重新输入！");
        }
        rotationChartInfo.setChartId(StringUtil.getCommonCode(2));
        rotationChartInfo.setIsDeleted(0);
        int count = rotationChartDao.addCharts(rotationChartInfo);
        if(0 == count){
            return AppResponse.bizError("新增轮播图失败,请重试");
        }
        return AppResponse.success("新增轮播图成功");
    }

    /**
     * deleteChart 删除轮播图
     * @param chartId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-27
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteChart(String chartId,String userId){
      List<String> listId = Arrays.asList(chartId.split(","));
      AppResponse appResponse = AppResponse.success("轮播图删除成功");
      int count = rotationChartDao.deleteChart(listId,userId);
      if (0 == count){
           appResponse = AppResponse.bizError("轮播图删除失败");
      }
     return appResponse;
    }

    /**
     * listCharts 轮播图列表查询
     * @param rotationChartInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-28
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listCharts(RotationChartInfo rotationChartInfo){
        PageHelper.startPage(rotationChartInfo.getPageNum(),rotationChartInfo.getPageSize());
        List<RotationChartInfo> rotationChartInfoList = rotationChartDao.listChartsByPage(rotationChartInfo);
        //包装Page对象
        PageInfo<RotationChartInfo> PageData = new PageInfo<RotationChartInfo>(rotationChartInfoList);
        return AppResponse.success("轮播图列表查询成功",PageData);
    }

    /**
     * updatePictureStatus 轮播图状态修改
     * @param chartId
     * @param pictureStatus
     * @param userId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-28
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updatePictureStatus(String chartId,int pictureStatus,String userId){
        List<String> listId = Arrays.asList(chartId.split(","));
        AppResponse appResponse = AppResponse.success("修改状态成功");
        //修改图片状态
       int count = rotationChartDao.updatePictureStatus(listId,pictureStatus,userId);
       if (0 == count){
           appResponse = AppResponse.versionError("数据无变化，请刷新");
           return appResponse;
       }
       return appResponse;
    }

    /**
     * listSelectGoods 商品列表查询
     * @param rotationChartInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-28
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listSelectGoods(RotationChartInfo rotationChartInfo){
        PageHelper.startPage(rotationChartInfo.getPageNum(),rotationChartInfo.getPageSize());
        List<RotationChartInfo> goodsInfoList = rotationChartDao.listSelectGoodsByPage(rotationChartInfo);
        //包装Page对象
        PageInfo<RotationChartInfo> PageData = new PageInfo<RotationChartInfo>(goodsInfoList);
        return AppResponse.success("商品列表查询成功",PageData);
    }
}
