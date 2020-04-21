package com.xzsd.pc.goodsCategory.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.base.entity.Tree;
import com.xzsd.pc.goodsCategory.dao.GoodsCategoryDao;
import com.xzsd.pc.goodsCategory.entity.GoodsCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class GoodsCategoryService {

    @Resource
     private GoodsCategoryDao goodsCategoryDao;

    /**
     * listCategory 查询分类列表
     *
     * @return AppResponse
     * @author chenchaotoa
     * @Date 2020-04-04
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listCategory( ){
        List<GoodsCategory> goodsCategoryList =  goodsCategoryDao.listCategory();
        return AppResponse.success("查询成功",goodsCategoryList);
    }

    /**
     * addGoodsCategory 商品分类新增
     * @param goodsCategory
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-04
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsCategory(GoodsCategory goodsCategory){
       //检测分类id是否存在
        int countCateName = goodsCategoryDao.countCateName(goodsCategory);
        if (countCateName != 0){
            return AppResponse.bizError("分类已存在，请重新输入");
        }

        goodsCategory.setCateId(StringUtil.getCommonCode(2));
        goodsCategory.setIsDeleted(0);
        int count = goodsCategoryDao.addGoodsCategory(goodsCategory);
        if (count == 0 ){
            return AppResponse.bizError("分类新增失败");
        }
        return AppResponse.success("分类新增成功");
    }

    /**
     * updateGoodsCategory 商品分类修改
     * @param goodsCategory
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-04
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsCategory(GoodsCategory goodsCategory){
      AppResponse appResponse = AppResponse.success("商品分类修改成功");
      int count = goodsCategoryDao.updateGoodsCategory(goodsCategory);
      if (count == 0){
          appResponse = AppResponse.bizError("商品分类修改失败");
          return appResponse;
      }
      return appResponse;
    }

    /**
     * findGoodsCategoryById 商品分类详情
     * @param cateId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-04
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse findGoodsCategoryById(String cateId){
        GoodsCategory goodsCategory = goodsCategoryDao.findGoodsCategoryById(cateId);
        return AppResponse.success("商品分类详情查询成功！",goodsCategory);
    }

    /**
     * deleteGoodsCategory 商品分类删除
     * @param goodsCategory
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-04
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoodsCategory(GoodsCategory goodsCategory){
         String isParent = goodsCategoryDao.isParent(goodsCategory);
         if (isParent != null){
             return AppResponse.bizError("删除失败，一级分类有子类时不允许删除");
         }
         AppResponse appResponse = AppResponse.success("商品分类删除成功！");
         int count = goodsCategoryDao.deleteGoodsCategory(goodsCategory);
         if (count == 0){
             appResponse = AppResponse.bizError("商品分类删除失败");
             return appResponse;
         }
         return appResponse;
    }
}
