package com.xzsd.app.clientShopCart.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.clientShopCart.dao.ClientShopCartDao;
import com.xzsd.app.clientShopCart.entity.ClientShopCartInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 购物车管理实现类
 * @Author chenchaotao
 * @Date 2020-04-14
 */
@Service
public class ClientShopCartService {
    @Resource
    private ClientShopCartDao clientShopCartDao;
    /**
     * addShoppingCart 新增购物车
     * @param clientShopCartInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-14
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addShoppingCart(ClientShopCartInfo clientShopCartInfo) {
        // 检验商品是否还有库存
        int countStock = clientShopCartDao.findStock(clientShopCartInfo);
        if (countStock < clientShopCartInfo.getCartGoodsCount()){
            return AppResponse.bizError("商品库存不够，添加购物车失败");
        }
        // 校验商品编号是否存在
        int countGoodsId = clientShopCartDao.countGoodsId(clientShopCartInfo);
        if(0 != countGoodsId) {
            return AppResponse.bizError("商品已存在！");
        }
        clientShopCartInfo.setUserId(SecurityUtils.getCurrentUserId());
        clientShopCartInfo.setShopCartId(StringUtil.getCommonCode(2));
        clientShopCartInfo.setIsDeleted(0);
        // 购物车新增
        int count = clientShopCartDao.addShoppingCart(clientShopCartInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * listShoppingCarts 查询购物车列表（分页）
     * @param clientShopCartInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-14
     */
    public AppResponse listShoppingCarts(ClientShopCartInfo clientShopCartInfo){
        PageHelper.startPage(clientShopCartInfo.getPageNum(), clientShopCartInfo.getPageSize());
        List<ClientShopCartInfo> clientShopCartInfoList = clientShopCartDao.listShoppingCartsByPage(clientShopCartInfo);
        //包装Page对象
        PageInfo<ClientShopCartInfo> pageData = new PageInfo<ClientShopCartInfo>(clientShopCartInfoList);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * updateShoppingCart 修改购物车
     * @param clientShopCartInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-14
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateShoppingCart(ClientShopCartInfo clientShopCartInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        //修改购物车
        int count = clientShopCartDao.updateShoppingCart(clientShopCartInfo);
        if (0 == count){
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * deleteShoppingCart 删除商品
     * @param shopCartId
     * @param userId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-14
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteShoppingCart(String shopCartId,String userId) {
        List<String> listCode = Arrays.asList(shopCartId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除购物车
        int count = clientShopCartDao.deleteShoppingCart(listCode,userId);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }
}
