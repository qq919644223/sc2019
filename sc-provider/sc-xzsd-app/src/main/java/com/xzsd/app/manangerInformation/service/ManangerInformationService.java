package com.xzsd.app.manangerInformation.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.manangerInformation.dao.ManangerInformationDao;
import com.xzsd.app.manangerInformation.entity.ManangerInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 店长个人信息
 * @author chenchaotao
 * @time 2020-04-22
 */
@Service
public class ManangerInformationService {
    @Resource
    private ManangerInformationDao manangerInformationDao;
    /**
     * listManangerDrivers 查询店长门下的司机信息
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-22
     */
    public AppResponse listManangerDrivers(){
        //获取当前登录id
        String userId = SecurityUtils.getCurrentUserId();
        //获取当前登录店长的用户编号查出店长所在区域
        String areaId = manangerInformationDao.findAreaId(userId);
        List<ManangerInformation> ManangerDriverList = manangerInformationDao.listManangerDrivers(areaId);
        return AppResponse.success("查询成功",ManangerDriverList);
    }

}
