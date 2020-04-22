package com.xzsd.app.driverHome.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.driverHome.dao.DriverHomeDao;
import com.xzsd.app.driverHome.entity.DriverHomeInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 司机管理
 * @author chenchaotao
 * @time 2020-04-22
 */
@Service
public class DriverHomeService {
    @Resource
    private DriverHomeDao driverHomeDao;
    /**
     * listDriverStores 查询司机负责的门店信息
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-22
     */
    public AppResponse listDriverStores(){
        //获取当前登录id
        String userId = SecurityUtils.getCurrentUserId();
        //获取当前登录司机的用户编号查出司机所在区域
        String areaId = driverHomeDao.findAreaId(userId);
        List<DriverHomeInfo> ManangerDriverList = driverHomeDao.listDriverStores(areaId);
        return AppResponse.success("查询成功",ManangerDriverList);
    }
}
