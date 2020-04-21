package com.xzsd.pc.store.dao;


import com.xzsd.pc.store.entity.AreaInfo;
import com.xzsd.pc.store.entity.CityInfo;
import com.xzsd.pc.store.entity.ProvinceInfo;
import com.xzsd.pc.store.entity.StoreInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreDao {
    /**
     * 获取所有门店信息
     * @param  storeInfo
     * @return 所有门店信息
     */
    List<StoreInfo> listStoreByPage(StoreInfo storeInfo);

    /**
     * 统计营业执照或店长编号数量
     * @param storeInfo
     * @return
     */
    int countCode(StoreInfo storeInfo);

    /**
     * 新增门店信息
     * @param storeInfo
     * @return
     */
    int addStore(StoreInfo storeInfo);

    /**
     * 修改门店信息
     * @param storeInfo 门店信息
     * @return 修改结果
     */
    int updateStoreById(StoreInfo storeInfo);

    /**
     * 删除门店
     * @param listCode 选中的门店编号集合
     * @param Id 更新人
     * @return
     */
    int deleteStore(@Param("listCode") List<String> listCode, @Param("Id")String Id);

    /**
     * 查询门店详情
     * @param storeId 门店编号
     * @return 查询结果
     */
    StoreInfo findStoreById(@Param("storeId")String storeId);

    /**
     * 查询省市区下拉列表
     * @param cateId
     * @return 省市区列表信息
     */
    List<AreaInfo> listArea(@Param("cateId") String cateId);

}
