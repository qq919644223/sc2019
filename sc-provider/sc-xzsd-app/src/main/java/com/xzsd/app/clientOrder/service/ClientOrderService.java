package com.xzsd.app.clientOrder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.entity.GoodsEvaluate;
import com.xzsd.app.clientOrder.entity.GoodsInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 订单管理实现类
 * @Author chenchaotao
 * @Date 2020-04-21
 */
@Service
public class ClientOrderService {
    @Resource
    private ClientOrderDao clientOrderDao;
    /**
     * addOrder 新增订单
     * @param goodsId
     * @param goodsPrice
     * @param clientGoodsNum
     * @param storeId
     * @param userId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addOrder(String goodsId,String goodsPrice,String clientGoodsNum,String storeId,String userId) {
        List<String> listGoodsId = Arrays.asList(goodsId.split(","));
        List<String> listGoodsPrice = Arrays.asList(goodsPrice.split(","));
        List<String> listClientGoodsNum = Arrays.asList(clientGoodsNum.split(","));
        List<ClientOrderInfo> listOrder = new ArrayList<>();
        //先检验订单中的商品是否还有库存，若其中一个商品没有库存，则下单失败
        List<Integer> countStock = clientOrderDao.countStock(listGoodsId);
        if (countStock.size() == 0){
            return AppResponse.bizError("订单中所有商品都没有库存，下单失败");
        }
        for (int i = 0; i < countStock.size(); i++) {
            if (countStock.get(i) == 0){
                return AppResponse.bizError("订单中商品编号为"+listGoodsId.get(i)+"已没有库存，下单失败");
            }
        }
        //订单商品总价
        double totalPrice = 0.0f;
        //订单总商品数量
        int totalCount = 0;
        String orderId = StringUtil.getCommonCode(2);
        for (int i = 0; i < listGoodsId.size(); i++) {
            int goodsNum = Integer.parseInt(listClientGoodsNum.get(i));
            totalCount+=goodsNum;
            String goodsTotalPrice = String.valueOf(new BigDecimal(listGoodsPrice.get(i)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()*goodsNum);
            listOrder.add(i,new ClientOrderInfo(StringUtil.getCommonCode(2),orderId,userId,listGoodsId.get(i),listGoodsPrice.get(i),Integer.parseInt(listClientGoodsNum.get(i)),goodsTotalPrice,countStock.get(i)));
            totalPrice+=new BigDecimal(listGoodsPrice.get(i)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()*goodsNum;
        }
        String orderPrice = String.valueOf(totalPrice);
        // 新增订单到订单表
        int count = clientOrderDao.addOrder(orderId,orderPrice,storeId,userId,totalCount);
        // 新增订单到订单详情表
        int countDetail = clientOrderDao.addOrderDetail(listOrder,storeId);
        // 新增订单完修改商品库存
        int countGoodsStock = clientOrderDao.updateGoodsStock(listOrder);
        // 新增完订单删除购物车中的商品
        int countCartGoods = clientOrderDao.deleteCartGoods(listGoodsId,userId);
        if(0 == count || 0 == countDetail || 0 == countGoodsStock || 0 == countCartGoods) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * listOrder 查询订单列表（分页）
     * @param clientOrderInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-18
     */
    public AppResponse listOrder(ClientOrderInfo clientOrderInfo){
        PageHelper.startPage(clientOrderInfo.getPageNum(), clientOrderInfo.getPageSize());
        List<ClientOrderInfo> goodsEvaluatesList = clientOrderDao.listOrderByPage(clientOrderInfo);
        //包装Page对象
        PageInfo<ClientOrderInfo> pageData = new PageInfo<ClientOrderInfo>(goodsEvaluatesList);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * updateOrderState 修改订单状态
     * @param clientOrderInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-22
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(ClientOrderInfo clientOrderInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        //获取用户id
        String userId = SecurityUtils.getCurrentUserId();
        clientOrderInfo.setLastModifiedBy(userId);
        //修改订单状态
        int count = clientOrderDao.updateOrderState(clientOrderInfo);
        if (0 == count){
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * listOrderDeepen 查询订单详情
     * @param orderId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-22
     */
    public AppResponse listOrderDeepen(String orderId){
        ClientOrderInfo clientOrderInfoList = clientOrderDao.listOrderDeepen(orderId);
        return AppResponse.success("查询成功！",clientOrderInfoList);
    }

    /**
     * listGoodsForEvaluate 查询订单评价商品信息列表
     * @param orderId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-22
     */
    public AppResponse listGoodsForEvaluate(String orderId){
        List<GoodsInfo> listGoodsForEvaluate = clientOrderDao.listGoodsForEvaluate(orderId);
        return AppResponse.success("查询成功！",listGoodsForEvaluate);
    }

    /**
     * addGoodsEvaluate 新增订单商品评价
     * @param goodsEvaluateList
     * @param orderId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-23
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsEvaluate(List<GoodsEvaluate> goodsEvaluateList,String orderId) {
        //获取当前登录人id
        String userId = SecurityUtils.getCurrentUserId();
        // 评价新增
        int count = clientOrderDao.addGoodsEvaluate(goodsEvaluateList,userId,orderId);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }
}
