package cn.zokoo.flow.entity.vo;

/**
 * @Description: 驳回的实体VO
 * @Author: june
 * @Since:9:19 2020/02/8
 * 爱拼才会赢 2018 ~ 2030 版权所有
 */
public class BackTaskVo extends BaseProcessVo {

    /**
     * 需要驳回的节点id 必填
     */
    private String distFlowElementId;

    public String getDistFlowElementId() {
        return distFlowElementId;
    }

    public void setDistFlowElementId(String distFlowElementId) {
        this.distFlowElementId = distFlowElementId;
    }
}
