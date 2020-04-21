package com.xzsd.pc.store.service;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.RandomUtil;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.customer.dao.CustomerDao;
import com.xzsd.pc.store.dao.StoreDao;
import com.xzsd.pc.store.entity.AreaInfo;
import com.xzsd.pc.store.entity.CityInfo;
import com.xzsd.pc.store.entity.ProvinceInfo;
import com.xzsd.pc.store.entity.StoreInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * @Description  门店信息管理实现类
 * @Author chenchaotao
 * @Date 2020-04-04
 */
@Service
public class StoreService {
     @Resource
     private StoreDao storeDao;
     @Resource
     private CustomerDao customerDao;
    /**
     * listStore 查询门店信息列表（分页）
     * @param  storeInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-04
     */
    public AppResponse listStore(StoreInfo storeInfo){
        //获取当前登录人的角色编号
        String userId = SecurityUtils.getCurrentUserId();
        int role = customerDao.getUserRole(userId);
        storeInfo.setUserId(userId);
        storeInfo.setRole(role);
        PageHelper.startPage(storeInfo.getPageNum(), storeInfo.getPageSize());
        List<StoreInfo> storeInfoList = storeDao.listStoreByPage(storeInfo);
        //包装Page对象
        PageInfo<StoreInfo> pageData = new PageInfo<StoreInfo>(storeInfoList);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * addStore 门店信息新增
     * @param storeInfo
     * @return AppResponse
     * @author chenchaotao
     * @date 2020-04-11
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addStore(StoreInfo storeInfo) {
        //检验营业执照或店长编号是否已存在
        int countCode = storeDao.countCode(storeInfo);
        if (0 != countCode){
            return AppResponse.bizError("新增门店失败,营业执照或店长编号已存在！");
        }
        storeInfo.setInvitationCode(RandomUtil.radmonkey(6));
        storeInfo.setStoreId(StringUtil.getCommonCode(2));
        storeInfo.setIsDeleted(0);
        // 新增门店
        int count = storeDao.addStore(storeInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * findStoreById 查询门店详情
     * @param storeId
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-11
     */
    public AppResponse findStoreById(String storeId){
        StoreInfo storeInfo = storeDao.findStoreById(storeId);
        return AppResponse.success("查询成功！",storeInfo);
    }

    /**
     * updateStoreById 修改门店信息
     * @param storeInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-11
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateStoreById(StoreInfo storeInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        //检验营业执照是否已存在
        int countLicenseCode = storeDao.countCode(storeInfo);
        if (0 != countLicenseCode){
            return AppResponse.bizError("修改门店失败，营业执照已存在");
        }
        // 修改门店信息
        int count = storeDao.updateStoreById(storeInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * deleteStore 删除门店
     * @param storeId
     * @param Id
     * @return AppResponse
     * @Author chenchaotao
     * @Data 2020-04-11
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteStore(String storeId,String Id){
        List<String> listCode = Arrays.asList(storeId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        //删除门店
        int count = storeDao.deleteStore(listCode,Id);
        if (0 == count){
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }


    /**
     * listArea 查询省市区下拉列表
     * @param cateId
     * @return AppResponse
     * @author chenchaotao
     * @date 2020-04-11
     */
    public AppResponse listArea(String cateId){
        List<AreaInfo> areaInfoList = storeDao.listArea(cateId);
        return AppResponse.success("查询成功",areaInfoList);
    }
}
