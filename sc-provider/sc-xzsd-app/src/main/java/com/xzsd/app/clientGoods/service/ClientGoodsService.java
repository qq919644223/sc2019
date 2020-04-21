package com.xzsd.app.clientGoods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientGoods.dao.ClientGoodsDao;
import com.xzsd.app.clientGoods.entity.ClientGoodsInfo;
import com.xzsd.app.clientGoods.entity.FirstGoodsClassify;
import com.xzsd.app.clientGoods.entity.GoodsEvaluates;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 商品管理实现类
 * @Author chenchaotao
 * @Date 2020-04-18
 */
@Service
public class ClientGoodsService {
    @Resource
    private ClientGoodsDao clientGoodsDao;

    /**
     * getGoods 查询商品详情
     * @param goodsId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-13
     */
    public AppResponse getGoods(String goodsId){
        ClientGoodsInfo clientGoodsInfo = clientGoodsDao.getGoods(goodsId);
        return AppResponse.success("查询成功！",clientGoodsInfo);
    }

    /**
     * listGoodsEvaluates 查询商品评价列表（分页）
     * @param goodsEvaluates
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-18
     */
    public AppResponse listGoodsEvaluates(GoodsEvaluates goodsEvaluates){
        PageHelper.startPage(goodsEvaluates.getPageNum(), goodsEvaluates.getPageSize());
        List<GoodsEvaluates> goodsEvaluatesList = clientGoodsDao.listGoodsEvaluatesByPage(goodsEvaluates);
        //包装Page对象
        PageInfo<GoodsEvaluates> pageData = new PageInfo<GoodsEvaluates>(goodsEvaluatesList);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * listOneGoodsClassify 查询一级商品分类列表
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-13
     */
    public AppResponse listOneGoodsClassify(){
        List<FirstGoodsClassify> firstGoodsClassifyList = clientGoodsDao.listOneGoodsClassify();
        return AppResponse.success("查询成功",firstGoodsClassifyList);
    }

    /**
     * listGetClassGoods
     * @param classifyId
     * @return AppResponse
     * @author chenchaotao
     * @date 2020-04-14
     */
    public AppResponse listGetClassGoods(String classifyId){
        List<FirstGoodsClassify> firstGoodsClassifyList =  clientGoodsDao.listGetClassGoods(classifyId);
        return AppResponse.success("查询成功",firstGoodsClassifyList);
    }
}
