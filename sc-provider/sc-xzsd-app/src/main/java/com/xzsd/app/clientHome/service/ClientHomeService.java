package com.xzsd.app.clientHome.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientHome.dao.ClientHomeDao;
import com.xzsd.app.clientHome.entity.ClientHomeInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 客户端首页管理实现类
 * @Author chenchaotao
 * @Date 2020-03-25
 */
@Service
public class ClientHomeService {
    @Resource
    private ClientHomeDao clientHomeDao;
    /**
     * listRotationCharHome 查询首页轮播图
     * @param clientHomeInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-13
     */
    public AppResponse listRotationCharHome(ClientHomeInfo clientHomeInfo){
        List<ClientHomeInfo> clientHomeInfoList = clientHomeDao.listRotationCharHome(clientHomeInfo);
        return AppResponse.success("查询成功",clientHomeInfoList);
    }

    /**
     * listHotGoods 查询热门商品（分页）
     * @param clientHomeInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-13
     */
    public AppResponse listHotGoods(ClientHomeInfo clientHomeInfo){
        List<ClientHomeInfo> clientHomeInfoList = clientHomeDao.listHotGoods(clientHomeInfo);
        return AppResponse.success("查询成功",clientHomeInfoList);
    }

}
