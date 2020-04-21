package com.xzsd.pc.goodsCategory.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goodsCategory.entity.GoodsCategory;
import com.xzsd.pc.goodsCategory.service.GoodsCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description 商品分类管理
 * @author chenchaotao
 * @Date 2020-03-29
 */
@RestController
@RequestMapping("/goodsCategory")
public class GoodsCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsCategoryController.class);
    @Resource
    private GoodsCategoryService goodsCategoryService;
    /**
     * listCategory 分类列表查询
     *
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-29
     */
    @PostMapping("/listCategory")
    public AppResponse listCategory(){
        try{
            return goodsCategoryService.listCategory();
        }catch (Exception e){
            logger.error("查询分类列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * addGoodsgory 商品分类新增
     * @param goodsCategory
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-04
     */
     @PostMapping("/addGoodsCategory")
    public AppResponse addGoodsCategory(GoodsCategory goodsCategory){
         try{
             String userId = SecurityUtils.getCurrentUserId();
             goodsCategory.setCreateBy(userId);
             return goodsCategoryService.addGoodsCategory(goodsCategory);
         }catch (Exception e){
             logger.error("商品分类新增失败",e);
             System.out.println(e.toString());
             throw e;
         }
     }

    /**
     * updateGoodsCategory 商品分类修改
     * @param goodsCategory
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-04
     */
     @PostMapping("updateGoodsCategory")
    public AppResponse updateGoodsCategory(GoodsCategory goodsCategory){
          try{
             String userId = SecurityUtils.getCurrentUserId();
             goodsCategory.setLastModifiedBy(userId);
             return goodsCategoryService.updateGoodsCategory(goodsCategory);
          }catch (Exception e){
             logger.error("商品分类修改失败",e);
             System.out.println(e.toString());
             throw e;
          }
     }

    /**
     * findGoodsCategoryById 商品分类详情
     * @param cateId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-04
     */
     @PostMapping("findGoodsCategoryById")
     public AppResponse findGoodsCategoryById(String cateId){
         try{
             return goodsCategoryService.findGoodsCategoryById(cateId);
         }catch (Exception e){
             logger.error("商品分类详情查询失败",e);
             System.out.println(e.toString());
             throw e;
         }
     }

    /**
     * deleteGoodsCategory 商品分类删除
     * @param goodsCategory
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04=04
     */
     @PostMapping("deleteGoodsCategory")
    public AppResponse deleteGoodsCategory(GoodsCategory goodsCategory){
         try {
            String userId = SecurityUtils.getCurrentUserId();
            goodsCategory.setLastModifiedBy(userId);
            return goodsCategoryService.deleteGoodsCategory(goodsCategory);
         }catch (Exception e){
             logger.error("商品分类删除成功",e);
             System.out.println(e.toString());
             throw e;
         }
     }

}
