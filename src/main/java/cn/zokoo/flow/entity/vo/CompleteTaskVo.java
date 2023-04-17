package cn.zokoo.flow.entity.vo;


import java.util.Map;

/**
 * @author : june
 * @title: : CompleteTaskVo
 * @projectName : flowable
 * @description: 执行任务Vo
 * @date : 2020/02/1315:27
 */
public class CompleteTaskVo extends BaseProcessVo {
    /**
     * 任务参数 选填
     */
    private Map<String, Object> variables;

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
