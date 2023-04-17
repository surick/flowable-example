package cn.zokoo.flow.entity.vo;


/**
 * @author : june
 * @title: : TurnTaskVo
 * @projectName : flowable
 * @description: 转办Vo
 * @date : 2020/02/1315:34
 */
public class TurnTaskVo extends BaseProcessVo {

    /**
     * 被转办人工号 必填
     */
    private String turnToUserId;
    public String getTurnToUserId() {
        return turnToUserId;
    }
    public void setTurnToUserId(String turnToUserId) {
        this.turnToUserId = turnToUserId;
    }
}
