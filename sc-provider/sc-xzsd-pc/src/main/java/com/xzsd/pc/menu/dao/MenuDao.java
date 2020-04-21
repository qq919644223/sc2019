package com.xzsd.pc.menu.dao;

import com.xzsd.pc.menu.entity.MenuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuDao {
    /**
     * 统计菜单名称数量
     * @param menuInfo
     * @return
     */
    int countMenuName(MenuInfo menuInfo);

    /**
     * 新增菜单
     * @param menuInfo
     * @return
     */
    int addMenu(MenuInfo menuInfo);

    /**
     * 获取所有菜单信息
     * @return 所有菜单信息
     */
    List<MenuInfo> listMenus();

    /**
     * 根据角色查询首页菜单列表
     * @return 菜单信息
     */
    List<MenuInfo> listMenuHome(MenuInfo menuInfo);

    /**
     * 查询菜单详情
     * @param menuId
     * @return
     */
    MenuInfo findMenuById(@Param("menuId") String menuId);

    /**
     * 修改菜单信息
     * @param menuInfo 菜单信息
     * @return 修改结果
     */
    int updateMenuById(MenuInfo menuInfo);

    /**
     * 删除菜单
     * @param menuId 菜单编号
     * @param Id 更新人
     * @return
     */
    int deleteMenu(@Param("menuId") String menuId, @Param("Id") String Id);
}
