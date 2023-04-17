package cn.zokoo.flow.entity.vo;

import java.io.Serializable;

/**
 * @author : june
 * @title: : QueryProcessInstanceVo
 * @projectName : flowable
 * @description: 查询流程实例VO
 * @date : 2020/02/2115:42
 */
public class ProcessInstanceQueryVo implements Serializable {

    private String formName;
    private String userCode;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}
