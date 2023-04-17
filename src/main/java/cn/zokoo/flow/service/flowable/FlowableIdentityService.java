package cn.zokoo.flow.service.flowable;

import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;

/**
 * @author : june
 * @projectName : flowable
 * @description: 用户组
 * @date : 2020/02/1411:46
 */
public interface FlowableIdentityService {
    /**
     * 添加用户
      * @param user
     */
    public void saveUser(User user) ;

    /**
     * 添加组
     * @param group
     */
    public void saveGroup(Group group) ;



}
