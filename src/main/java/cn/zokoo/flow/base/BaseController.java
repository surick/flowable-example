package cn.zokoo.flow.base;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.User;
import org.flowable.ui.common.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : june
 * @title: : BaseResource
 * @projectName : flowable
 * @description: TODO
 * @date : 2020/02/22 16:18
 */
public class BaseController {

    @Autowired
    protected RuntimeService runtimeService;

    public User getLoginUser() {
        User user = SecurityUtils.getCurrentUserObject();
        return user;
    }

    /**
     * 判断是否挂起状态
     * @param processInstanceId 流程实例id
     * @return
     */
    public boolean isSuspended(String processInstanceId) {
        boolean flag = true;
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance != null){
            flag = !processInstance.isSuspended();
        }
        return flag;
    }
}
