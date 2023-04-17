package cn.zokoo.flow.entity.vo;

import java.io.Serializable;

/**
 * @author : june
 * @title: : GroupVo
 * @projectName : flowable
 * @description: 组的VO
 * @date : 2020/02/313:59
 */
public class GroupVo implements Serializable {

    /**
     * 组的id
     */
    private String id;
    /**
     * 组的名称
     */
    private String groupName;
    /****************************扩展字段****************************/
    /**
     * 组的标识
     */
    private String groupSn;

}
