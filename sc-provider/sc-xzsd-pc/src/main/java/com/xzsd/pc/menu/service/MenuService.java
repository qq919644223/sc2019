package com.xzsd.pc.menu.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.customer.dao.CustomerDao;
import com.xzsd.pc.menu.dao.MenuDao;
import com.xzsd.pc.menu.entity.MenuInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
    @Resource
    private MenuDao menuDao;
    @Resource
    private CustomerDao customerDao;

    /**
     * listMenus 查询菜单列表
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-12
     */
    public AppResponse listMenus(){
        List<MenuInfo> menuInfoList = menuDao.listMenus();
        return AppResponse.success("查询成功",menuInfoList);
    }

    /**
     * listMenuHome 根据角色查询首页菜单列表
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-12
     */
    public AppResponse listMenuHome(){
        String userId = SecurityUtils.getCurrentUserId();
        int role = customerDao.getUserRole(userId);
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setRole(role);
        List<MenuInfo> menuInfoList = menuDao.listMenuHome(menuInfo);
        return AppResponse.success("查询成功",menuInfoList);
    }

    /**
     * findMenuById 查询菜单详情
     * @param menuId
     * @return AppResponse
     * @author chenchaotao
     * @Date 2020-04-12
     */
    public AppResponse findMenuById(String menuId){
        MenuInfo menuInfo = menuDao.findMenuById(menuId);
        return AppResponse.success("查询成功！",menuInfo);
    }

    /**
     * addMenu 新增菜单
     * @param menuInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-12
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addMenu(MenuInfo menuInfo) {
        //检验菜单名称是否已存在
        int countMenuName = menuDao.countMenuName(menuInfo);
        if (0 != countMenuName){
            return AppResponse.bizError("菜单名已存在，请重新输入");
        }
        menuInfo.setMenuId(StringUtil.getCommonCode(2));
        menuInfo.setIsDeleted(0);
        // 新增菜单
        int count = menuDao.addMenu(menuInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * updateMenuById 修改菜单信息
     * @param menuInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateMenuById(MenuInfo menuInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        //检验菜单名称是否已存在
        int countMenuName = menuDao.countMenuName(menuInfo);
        if (0 != countMenuName){
            return AppResponse.bizError("菜单名已存在，请重新输入");
        }
        // 修改菜单信息
        int count = menuDao.updateMenuById(menuInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据无变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * deleteMenu 删除用户
     * @param menuId
     * @param Id
     * @return AppResponse
     * @Author chenchaotao
     * @Data 2020-04-12
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteMenu(String menuId,String Id){
        AppResponse appResponse = AppResponse.success("删除成功！");
        //删除菜单
        int count = menuDao.deleteMenu(menuId,Id);
        if (0 == count){
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

}
