package cn.zokoo.flow.entity.vo;

import java.io.Serializable;

/**
 * @author : june
 * @title: : TaskVo
 * @projectName : flowable
 * @description: 任务VO
 * @date : 2020/02/1315:11
 */
public class TaskQueryVo implements Serializable {

    /**
     * 用户工号
     */
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
