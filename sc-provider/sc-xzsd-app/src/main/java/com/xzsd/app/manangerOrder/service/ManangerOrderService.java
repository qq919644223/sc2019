package com.xzsd.app.manangerOrder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.manangerOrder.dao.ManangerOrderDao;
import com.xzsd.app.manangerOrder.entity.ManangerOrderInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 店长订单管理
 * @author chenchaotao
 * @time 2020-04-22
 */
@Service
public class ManangerOrderService {
    @Resource
    private ManangerOrderDao manangerOrderDao;
    /**
     * listManagerOrders 查询店长订单列表
     * @param manangerOrderInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-22
     */
    public AppResponse listManagerOrders(ManangerOrderInfo manangerOrderInfo){
        //获取当前登录id
        String userId = SecurityUtils.getCurrentUserId();
        //获取当前登录店长的用户编号查出其门店编号
        String storeId = manangerOrderDao.findCode(userId);
        manangerOrderInfo.setStoreId(storeId);
        PageHelper.startPage(manangerOrderInfo.getPageNum(), manangerOrderInfo.getPageSize());
        List<ManangerOrderInfo> manangerOrderInfoList = manangerOrderDao.listManagerOrdersByPage(manangerOrderInfo);
        //包装Page对象
        PageInfo<ManangerOrderInfo> pageData = new PageInfo<ManangerOrderInfo>(manangerOrderInfoList);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * updateManangerOrderState 修改店长订单状态
     * @param manangerOrderInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-22
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateManangerOrderState(ManangerOrderInfo manangerOrderInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        //获取用户id
        String userId = SecurityUtils.getCurrentUserId();
        manangerOrderInfo.setLastModifiedBy(userId);
        //修改订单状态
        int count = manangerOrderDao.updateManangerOrderState(manangerOrderInfo);
        if (0 == count){
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * listManagerOrderDeepen 查询店长订单详情
     * @param orderId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-22
     */
    public AppResponse listManagerOrderDeepen(String orderId){
        ManangerOrderInfo manangerOrderInfo = manangerOrderDao.listManagerOrderDeepen(orderId);
        return AppResponse.success("查询成功",manangerOrderInfo);
    }
}
