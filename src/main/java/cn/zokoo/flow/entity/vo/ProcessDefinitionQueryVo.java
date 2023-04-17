package cn.zokoo.flow.entity.vo;

import java.io.Serializable;

/**
 * @author : june
 * @title: : ProcessDefinitionQueryVo
 * @projectName : flowable
 * @description: 流程定义查询Vo
 * @date : 2020/02/2617:49
 */
public class ProcessDefinitionQueryVo implements Serializable {

    private String name;
    private String modelKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }
}
