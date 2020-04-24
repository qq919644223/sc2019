package com.xzsd.pc.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;


import com.xzsd.pc.util.PasswordUtils;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.user.entity.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @DescriptionDemo 用户管理实现类
 * @Author chenchaotao
 * @Date 2020-03-24
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * addUser 新增用户
     * @param userInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-24
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addUser(UserInfo userInfo) {
        // 校验账号是否存在
        int countUser = userDao.countUser(userInfo);
        if(0 != countUser) {
            return AppResponse.bizError("用户账号或手机号已存在，请重新输入！");
        }
        //密码加密
        if (userInfo.getPassword() != null) {
            //查出数据库里原加密密码，如果不修改密码，密码无需再加加密
            String password = userDao.findPwd(userInfo);
            if (!userInfo.getPassword().equals(password)){
                String pwd = PasswordUtils.generatePassword(userInfo.getPassword());
                userInfo.setPassword(pwd);
            }
        }
        userInfo.setUserId(StringUtil.getCommonCode(2));
        userInfo.setIsDeleted(0);
        // 新增用户
        int count = userDao.addUser(userInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * deleteUser 删除用户
     * @param userId
     * @param Id
     * @return AppResponse
     * @Author chenchaotao
     * @Data 2020-03-24
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteUser(String userId,String Id){
        List<String> listCode = Arrays.asList(userId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        //删除用户
        int count = userDao.deleteUser(listCode,Id);
        if (0 == count){
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * updateUserById 修改用户信息
     * @param userInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUserById(UserInfo userInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        // 校验账号是否存在
        int countUser = userDao.countUser(userInfo);
        if(0 != countUser) {
            return AppResponse.bizError("用户账号或手机号已存在，请重新输入！");
        }
        //将新密码加密
        if(userInfo.getPassword() != null){
            String password = userDao.findPwd(userInfo);
            if (!userInfo.getPassword().equals(password)) {
                String pwd = PasswordUtils.generatePassword(userInfo.getPassword());
                userInfo.setPassword(pwd);
            }
        }
        // 修改用户信息
        int count = userDao.updateUserById(userInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * listUsers 查询用户列表（分页）
     * @param userInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-26
     */
    public AppResponse listUsers(UserInfo userInfo){
        PageHelper.startPage(userInfo.getPageNum(),userInfo.getPageSize());
        List<UserInfo> userInfoList = userDao.listUsersByPage(userInfo);
        //包装对象
        PageInfo<UserInfo> pageData = new PageInfo<UserInfo>(userInfoList);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * findUserById 查询用户详情
     * @param userId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-03-26
     */
    public AppResponse findUserById(String userId){
        UserInfo userInfo = userDao.findUserById(userId);
        return AppResponse.success("查询成功！",userInfo);
    }
}
