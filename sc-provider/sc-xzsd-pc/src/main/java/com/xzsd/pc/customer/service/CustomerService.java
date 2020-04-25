package com.xzsd.pc.customer.service;


import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.customer.dao.CustomerDao;
import com.xzsd.pc.customer.entity.CustomerInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @DescriptionCustomer 客户管理实现类
 * @Author chenchaotao
 * @Date 2020-04-04
 */
@Service
public class CustomerService {
    @Resource
    private CustomerDao customerDao;
    /**
     * listGoods 查询客户列表（分页）
     * @param customerInfo
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-04
     */
    public AppResponse listCustomers(CustomerInfo customerInfo){
        //查询当前登录的角色
        String userId = SecurityUtils.getCurrentUserId();
        int role = customerDao.getUserRole(userId);
        customerInfo.setRole(role);
        //如果是店长登录，则通过邀请码查询自己门店下的客户
        if (customerInfo.getRole() == 1) {
            String invitationCode = customerDao.findCode(userId);
            if (invitationCode == null){
                return AppResponse.bizError("还未有邀请码，请先新增门店信息");
            }
            customerInfo.setInvitationCode(invitationCode);
        }
        PageHelper.startPage(customerInfo.getPageNum(), customerInfo.getPageSize());
        List<CustomerInfo> customersInfoList = customerDao.listCustomersByPage(customerInfo);
        //包装Page对象
        PageInfo<CustomerInfo> pageData = new PageInfo<CustomerInfo>(customersInfoList);
        return AppResponse.success("查询成功",pageData);
    }
}
