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

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

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
     * @param shopCartId
     * @param storeId
     * @param userId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addOrder(String goodsId,String goodsPrice,String clientGoodsNum,String shopCartId,String storeId,String userId) {
        List<String> listGoodsId = Arrays.asList(goodsId.split(","));
        List<String> listGoodsPrice = Arrays.asList(goodsPrice.split(","));
        List<String> listClientGoodsNum = Arrays.asList(clientGoodsNum.split(","));
        List<String> listShopCartId = Arrays.asList(shopCartId.split(","));
        List<ClientOrderInfo> listOrder = new ArrayList<>();
        //校验客户是否绑定了门店邀请码
        String inviteCode = clientOrderDao.findInviteCode(userId);
        if ("".equals(inviteCode)){
            return AppResponse.bizError("未绑定门店邀请码，请先绑定再下单");
        }
        //先检验订单中的商品是否还有库存，若其中一个商品购买数量超过库存，则下单失败
        List<ClientOrderInfo> countStock = clientOrderDao.countStock(listGoodsId);
        //将查出来的库存和编号用map一一对应
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < countStock.size(); i++) {
            map.put(countStock.get(i).getGoodsId(),countStock.get(i).getStock());
        }
        for (int i = 0; i < countStock.size(); i++) {
            String goodsCode = countStock.get(i).getGoodsId();
            int stock = countStock.get(i).getStock();
            for (int j = 0; j < listGoodsId.size(); j++) {
                if (listGoodsId.get(j).equals(goodsCode)){
                    if (Integer.parseInt(listClientGoodsNum.get(j)) > stock){
                        return AppResponse.bizError("订单中有商品库存不足，请重新下单");
                    }
                }
            }
        }
        //订单商品总价
        double totalPrice = 0.0f;
        //订单总商品数量
        int totalCount = 0;
        String orderId = StringUtil.getCommonCode(2);
        //将订单请求参数封装进listOrder，方便sql的for-each插入
        for (int i = 0; i < listGoodsId.size(); i++) {
            int goodsNum = Integer.parseInt(listClientGoodsNum.get(i));
            totalCount+=goodsNum;
            String goodsTotalPrice = String.valueOf(new BigDecimal(listGoodsPrice.get(i)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()*goodsNum);
            listOrder.add(i,new ClientOrderInfo(StringUtil.getCommonCode(2),orderId,userId,listGoodsId.get(i),listGoodsPrice.get(i),Integer.parseInt(listClientGoodsNum.get(i)),goodsTotalPrice,map.get(listGoodsId.get(i))));
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
        if (!"0".equals(shopCartId)){
            int countCartGoods = clientOrderDao.deleteCartGoods(listShopCartId,userId);
            if (0 == countCartGoods){
                return AppResponse.bizError("购物车清空失败，请重试");
            }
        }
        if(0 == count || 0 == countDetail || 0 == countGoodsStock) {
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
        String userId = SecurityUtils.getCurrentUserId();
        clientOrderInfo.setUserId(userId);
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
        //若取消了订单，则需返还相应的库存
        if ("1".equals(clientOrderInfo.getOrderStateId())){
            //查询该订单下每个商品的购买数量和相应商品的库存
            List <ClientOrderInfo> listGoodsNum = clientOrderDao.findGoodsNum(clientOrderInfo);
            //修改商品库存
            clientOrderInfo.setUserId(userId);
            int countStock = clientOrderDao.updateStock(listGoodsNum);
            if (0 == count || 0 == countStock){
                return AppResponse.versionError("数据无变化,清重试");
            }
        }
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
        // 获取当前登录人id
        String userId = SecurityUtils.getCurrentUserId();
        // 评价新增
        int count = clientOrderDao.addGoodsEvaluate(goodsEvaluateList);
        // 评价完更新订单状态为已完成已评价
        int countEvaluate = clientOrderDao.updateStatus(orderId,userId);
        // 计算评价后的商品星级
        List<Double> evaluateScoreList = clientOrderDao.countScore(goodsEvaluateList);
        for (int i = 0; i < evaluateScoreList.size(); i++) {
            goodsEvaluateList.get(i).setGoodsEvaluateScore(new BigDecimal(evaluateScoreList.get(i)).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
            goodsEvaluateList.get(i).setUserId(userId);
        }
        // 更新商品评价星级
        int countGoodsScore = clientOrderDao.updateScore(goodsEvaluateList);
        // 查出商品的购买数量，随后更新销售量
        ClientOrderInfo clientOrderInfo = new ClientOrderInfo();
        clientOrderInfo.setOrderId(orderId);
        clientOrderInfo.setUserId(userId);
        List<ClientOrderInfo> listGoodsNum = clientOrderDao.findGoodsNum(clientOrderInfo);
        //更新销售量
        int saleCount = clientOrderDao.updateSaleCount(listGoodsNum);
        if(0 == count || 0 == countEvaluate || 0 == countGoodsScore || 0 == saleCount) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }
}
