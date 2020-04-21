package com.xzsd.pc.order.service;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.customer.dao.CustomerDao;
import com.xzsd.pc.order.dao.OrderDao;
import com.xzsd.pc.order.entity.OrderInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
      @Resource
      private OrderDao orderDao;
      @Resource
      private CustomerDao customerDao;
    /**
     * listOrders 查询订单列表（分页）
     * @param orderInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-25
     */
    public AppResponse listOrders(OrderInfo orderInfo){
        //获取当前登录人的角色编号
        String userId = SecurityUtils.getCurrentUserId();
        int role = customerDao.getUserRole(userId);
        orderInfo.setRole(role);
        //如果是店长登录，则通过门店编码查询自己门店下的订单
        if (orderInfo.getRole() == 1) {
            OrderInfo storeCode = orderDao.findCode(userId);
            orderInfo.setStoreCode(storeCode.getStoreCode());
        }
        PageHelper.startPage(orderInfo.getPageNum(), orderInfo.getPageSize());
        List<OrderInfo> ordersInfoList = orderDao.listOrdersByPage(orderInfo);
        //包装Page对象
        PageInfo<OrderInfo> pageData = new PageInfo<OrderInfo>(ordersInfoList);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * findOrderById 查询商品详情
     * @param orderCode
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-05
     */
    public AppResponse findOrderById(String orderCode){
        List<OrderInfo> orderInfo = orderDao.findOrderById(orderCode);
        return AppResponse.success("查询成功！",orderInfo);
    }

    /**
     * updateOrderStatus 修改订单状态
     * @param orderCode
     * @param orderStatus
     * @param userId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderStatus(String orderCode,int orderStatus,String userId){
        List<String> listCode = Arrays.asList(orderCode.split(","));
        AppResponse appResponse = AppResponse.success("修改成功");
        //修改商品信息
        int count = orderDao.updateOrderStatus(listCode,orderStatus,userId);
        if (0 == count){
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }
}
