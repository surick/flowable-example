package cn.zokoo.flow.entity.vo;


/**
 * @author : june
 * @title: : DelegateTaskVo
 * @projectName : flowable
 * @description: 委派
 * @date : 2020/02/1315:51
 */
public class DelegateTaskVo extends BaseProcessVo {
    /**
     * 委派人
     */
    private String delegateUserCode;

    public String getDelegateUserCode() {
        return delegateUserCode;
    }

    public void setDelegateUserCode(String delegateUserCode) {
        this.delegateUserCode = delegateUserCode;
    }
}
