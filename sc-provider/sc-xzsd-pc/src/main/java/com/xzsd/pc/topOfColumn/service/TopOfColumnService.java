package com.xzsd.pc.topOfColumn.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.topOfColumn.dao.TopOfColumnDao;
import com.xzsd.pc.topOfColumn.entity.TopOfColumnInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 顶部栏管理
 * @author chenchaotao
 * @time 2020-04-24
 */
@Service
public class TopOfColumnService {
    @Resource
    private TopOfColumnDao topOfColumnDao;
    /**
     * getTopOfColumn 查询顶部栏
     * @return AppResponse
     * @Author chenchaotao
     * @Date 2020-04-24
     */
    public AppResponse getTopOfColumn(){
        String userId = SecurityUtils.getCurrentUserId();
        TopOfColumnInfo topOfColumnInfo = topOfColumnDao.getTopOfColumn(userId);
        return AppResponse.success("查询成功！",topOfColumnInfo);
    }

}
