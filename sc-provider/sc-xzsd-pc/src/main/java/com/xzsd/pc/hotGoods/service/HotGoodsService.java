package com.xzsd.pc.hotGoods.service;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.hotGoods.dao.HotGoodsDao;
import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class HotGoodsService {
    @Resource
    private HotGoodsDao hotGoodsDao;
    /**
     * listHotGoods 查询热门位商品列表（分页）
     * @param hotGoodsInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-05
     */
    public AppResponse listHotGoods(HotGoodsInfo hotGoodsInfo){
        PageHelper.startPage(hotGoodsInfo.getPageNum(), hotGoodsInfo.getPageSize());
        List<HotGoodsInfo> hotGoodsInfoList = hotGoodsDao.listHotGoodsByPage(hotGoodsInfo);
        //包装Page对象
        PageInfo<HotGoodsInfo> pageData = new PageInfo<HotGoodsInfo>(hotGoodsInfoList);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * addHotGoods 新增商品
     * @param hotGoodsInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-05
     */

    @Transactional(rollbackFor = Exception.class)
    public AppResponse addHotGoods(HotGoodsInfo hotGoodsInfo) {
        // 校验商品编号或排序编号是否已存在
        int countCode = hotGoodsDao.countCode(hotGoodsInfo);
        if(0 != countCode) {
            return AppResponse.bizError("商品编号或排序号已存在，请重新输入！");
        }
        hotGoodsInfo.setHotGoodsId(StringUtil.getCommonCode(2));
        hotGoodsInfo.setIsDeleted(0);
        // 新增商品
        int count = hotGoodsDao.addHotGoods(hotGoodsInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * updateHotGoodsById 修改商品
     * @param hotGoodsInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-05
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateHotGoodsById(HotGoodsInfo hotGoodsInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        // 校验商品编号或排序编号是否已存在
        int countCode = hotGoodsDao.countCode(hotGoodsInfo);
        if(0 != countCode) {
            return AppResponse.bizError("商品编号或排序号已存在，请重新输入！");
        }
        //修改商品信息
        int count = hotGoodsDao.updateHotGoodsById(hotGoodsInfo);
        if (0 == count){
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * deleteHotGoods 删除商品
     * @param hotGoodsId
     * @param userId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-06
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteHotGoods(String hotGoodsId,String userId) {
        List<String> listCode = Arrays.asList(hotGoodsId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除用户
        int count = hotGoodsDao.deleteHotGoods(listCode,userId);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * findhotGoodsById 查询热门位商品详情
     * @param hotGoodsId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-06
     */
    public AppResponse findhotGoodsById(String hotGoodsId){
        HotGoodsInfo hotGoodsInfo = hotGoodsDao.findhotGoodsById(hotGoodsId);
        return AppResponse.success("查询成功！",hotGoodsInfo);
    }

    /**
     * listSelectGoods 商品列表查询
     * @param hotGoodsInfo
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-06
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listSelectGoods(HotGoodsInfo hotGoodsInfo){
        PageHelper.startPage(hotGoodsInfo.getPageNum(),hotGoodsInfo.getPageSize());
        List<HotGoodsInfo> goodsInfoList = hotGoodsDao.listSelectGoodsByPage(hotGoodsInfo);
        //包装Page对象
        PageInfo<HotGoodsInfo> PageData = new PageInfo<HotGoodsInfo>(goodsInfoList);
        return AppResponse.success("商品列表查询成功",PageData);
    }

    /**
     * findHotGoodsNum 查询热门位商品详情
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-06
     */
    public AppResponse findHotGoodsNum(){
        HotGoodsInfo hotGoodsInfo = hotGoodsDao.findHotGoodsNum();
        return AppResponse.success("查询成功！",hotGoodsInfo);
    }


    /**
     * updateHotGoodsNumber 修改热门商品展示数量
     * @param hotGoodsNum
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateHotGoodsNumber(String hotGoodsNum){
        AppResponse appResponse = AppResponse.success("修改成功");
        // 修改用户信息
        int count = hotGoodsDao.updateHotGoodsNumber(hotGoodsNum);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }
}
