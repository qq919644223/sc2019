package com.xzsd.pc.driver.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.customer.dao.CustomerDao;
import com.xzsd.pc.driver.dao.DriverDao;
import com.xzsd.pc.driver.entity.DriverInfo;
import com.xzsd.pc.store.dao.StoreDao;
import com.xzsd.pc.store.entity.AreaInfo;
import com.xzsd.pc.util.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @DescriptionDemo 司机信息管理实现类
 * @Author chenchaotao
 * @Date 2020-04-11
 */
@Service
public class DriverService {
    @Resource
    private DriverDao driverDao;

    @Resource
    private CustomerDao customerDao;

    /**
     * listDriver 查询司机列表（分页）
     * @param driverInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-11
     */
    public AppResponse listDriver(DriverInfo driverInfo){
        //获取当前登录人的角色编号
        String userId = SecurityUtils.getCurrentUserId();
        int role = customerDao.getUserRole(userId);
        driverInfo.setRole(role);
        driverInfo.setUserId(userId);
        PageHelper.startPage(driverInfo.getPageNum(),driverInfo.getPageSize());
        List<DriverInfo> driverInfoList = driverDao.listDriverByPage(driverInfo);
        //包装对象
        PageInfo<DriverInfo> pageData = new PageInfo<DriverInfo>(driverInfoList);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * addDriver 新增司机
     * @param driverInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-11
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addDriver(DriverInfo driverInfo) {
        // 校验账号和手机号是否已存在
        int count = driverDao.count(driverInfo);
        if(0 != count) {
            return AppResponse.bizError("用户账号或手机号已存在，请重新输入！");
        }
        //密码加密
        if (driverInfo.getPassword() != null) {
            String pwd = PasswordUtils.generatePassword(driverInfo.getPassword());
            driverInfo.setPassword(pwd);
        }
        driverInfo.setDriverId(StringUtil.getCommonCode(2));
        driverInfo.setIsDeleted(0);
        // 新增司机
        int countUser = driverDao.addDriver(driverInfo);
        int countDriver = driverDao.addUser(driverInfo);
        if(0 == countUser && 0 == countDriver) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * findDriverById 查询司机详情
     * @param driverId
     * @return
     * @author chenchaotao
     * @Date 2020-03-26
     */
    public AppResponse findDriverById(String driverId){
        DriverInfo driverInfo = driverDao.findDriverById(driverId);
        return AppResponse.success("查询成功！",driverInfo);
    }

    /**
     * updateDriverById 修改司机信息
     * @param driverInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-11
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateDriverById(DriverInfo driverInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        // 校验账号和手机号是否已存在
        int count = driverDao.count(driverInfo);
        if(0 != count) {
            return AppResponse.bizError("用户账号或手机号已存在，请重新输入！");
        }
        //将新密码加密
        if(driverInfo.getPassword() != null){
            String pwd = PasswordUtils.generatePassword(driverInfo.getPassword());
            driverInfo.setPassword(pwd);
        }
        // 修改司机信息
        int countDriver = driverDao.updateDriverById(driverInfo);
        //修改用户信息
        int countUser = driverDao.updateUserById(driverInfo);
        if (0 == countDriver || 0 == countUser) {
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * deleteDriver 删除司机
     * @param driverId
     * @param Id
     * @return AppResponse
     * @Author chenchaotao
     * @Data 2020-04-12
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteDriver(String driverId,String Id){
        List<String> listCode = Arrays.asList(driverId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        //删除司机
        int countDriver = driverDao.deleteDriver(listCode,Id);
        //删除用户
        int countUser = driverDao.deleteUser(listCode,Id);
        if (0 == countDriver || 0 == countUser){
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
        List<AreaInfo> areaInfoList = driverDao.listArea(cateId);
        return AppResponse.success("查询成功",areaInfoList);
    }
}
