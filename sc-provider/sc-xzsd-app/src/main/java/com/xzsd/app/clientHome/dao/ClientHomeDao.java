package com.xzsd.app.clientHome.dao;

import com.xzsd.app.clientHome.entity.ClientHomeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientHomeDao {
    /**
     * 查询热门商品展示数量
     * @return
     */
    int findHotGoodsNum();

    /**
     * 获取轮播图信息
     * @param clientHomeInfo
     * @return 轮播图信息
     */
    List<ClientHomeInfo> listRotationCharHome(ClientHomeInfo clientHomeInfo);

    /**
     * 获取热门商品信息
     * @param clientHomeInfo
     * @return 热门商品信息
     */
    List<ClientHomeInfo> listHotGoods(ClientHomeInfo clientHomeInfo);
}
