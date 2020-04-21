package com.xzsd.pc.goods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;

import com.xzsd.pc.goods.dao.GoodsDao;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goods.entity.GoodsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @DescriptionGoods 商品管理实现类
 * @Author chenchaotao
 * @Date 2020-03-25
 */
@Service
public class GoodsService {
     @Resource
    private GoodsDao goodsDao;
    /**
     * addGoods 新增商品
     * @param goodsInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoods(GoodsInfo goodsInfo) {
        // 校验书号是否存在
        int countBookNo = goodsDao.countBookNo(goodsInfo);
        if(0 != countBookNo) {
            return AppResponse.bizError("书号已存在，请重新输入！");
        }
        goodsInfo.setGoodsCode(StringUtil.getCommonCode(2));
        goodsInfo.setIsDeleted(0);
        // 新增商品
        int count = goodsDao.addGoods(goodsInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * deleteGoods 删除商品
     * @param goodsCode
     * @param userId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-21
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoods(String goodsCode,String userId) {
        List<String> listCode = Arrays.asList(goodsCode.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        //查询输入的商品编号中正处于轮播图或热门位的商品编号
        List<String> goodsCodeList = goodsDao.listHotChart(listCode);
        ArrayList listGoodsCode = new ArrayList<String>(listCode);
        //排除处于轮播图或热门位的商品编号
        listGoodsCode.removeAll(goodsCodeList);
        if (listGoodsCode.size() == 0){
            return AppResponse.bizError("商品删除失败，商品编号"+listCode+"正处于轮播图或热门位上，无法删除");
        }
        // 删除商品
        int count = goodsDao.deleteGoods(listGoodsCode, userId);
        if (0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        if (goodsCodeList.size() != 0 && listGoodsCode.size() != 0) {
            return AppResponse.success("部分商品删除成功，商品编号为" + goodsCodeList + "的商品正处于轮播图或热门位商品上，无法删除");
        }
        return appResponse;
    }
    /**
     * updateGoodsById 修改商品
     * @param goodsInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsById(GoodsInfo goodsInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        //修改商品信息
        int count = goodsDao.updateGoodsById(goodsInfo);
        if (0 == count){
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * listGoods 查询商品列表（分页）
     * @param goodsInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-25
     */
    public AppResponse listGoods(GoodsInfo goodsInfo){
            PageHelper.startPage(goodsInfo.getPageNum(), goodsInfo.getPageSize());
            List<GoodsInfo> goodsInfoList = goodsDao.listGoodsByPage(goodsInfo);
            //包装Page对象
            PageInfo<GoodsInfo> pageData = new PageInfo<GoodsInfo>(goodsInfoList);
            return AppResponse.success("查询成功",pageData);
    }

    /**
     * findGoodsById 查询商品详情
     * @param goodsCode
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-25
     */
    public AppResponse findGoodsById(String goodsCode){
        GoodsInfo goodsInfo = goodsDao.findGoodsById(goodsCode);
        return AppResponse.success("查询成功！",goodsInfo);
    }

    /**
     * updateGoodsStatus 修改商品状态
     * @param goodsCode
     * @param goodsStatus
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsStatus(String goodsCode,int goodsStatus,String userId){
        List<String> listCode = Arrays.asList(goodsCode.split(","));
        AppResponse appResponse = AppResponse.success("修改成功");
        //修改商品信息
        int count = goodsDao.updateGoodsStatus(listCode,goodsStatus,userId);
        if (0 == count){
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * listGoodsClassify 查询商品分类下拉框
     * @param classifyId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-13
     */
    public AppResponse listGoodsClassify(String classifyId){
        List<GoodsVO> goodsVOList = goodsDao.listGoodsClassify(classifyId);
        return AppResponse.success("查询成功",goodsVOList);
    }
}
