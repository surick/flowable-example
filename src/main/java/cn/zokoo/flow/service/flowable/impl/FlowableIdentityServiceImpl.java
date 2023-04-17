package cn.zokoo.flow.service.flowable.impl;

import cn.zokoo.flow.service.flowable.FlowableIdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.stereotype.Service;

/**
 * @author : june
 * @title: : FlowableIdentityServiceImpl
 * @projectName : flowable
 * @description: 用户组service
 * @date : 2020/02/1411:46
 */
@Service
public class FlowableIdentityServiceImpl extends BaseProcessService implements FlowableIdentityService {

    @Override
    public void saveUser(User user) {
        identityService.saveUser(user);
    }

    @Override
    public void saveGroup(Group group) {
        identityService.saveGroup(group);
    }

}
