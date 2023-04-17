package cn.zokoo.flow.mapper.flowable;

import cn.zokoo.flow.entity.vo.ProcessDefinitionQueryVo;
import cn.zokoo.flow.entity.vo.ProcessDefinitionVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author : june
 * @title: : IFlowableProcessInstentDao
 * @projectName : flowable
 * @description: 流程定义Dao
 * @date : 2020/02/23 17:09
 */
@Mapper
@Repository
public interface FlowableProcessDefinitionMapper {

    /**
     * 通过条件查询流程定义列表
     * @param params 参数
     * @return
     */
    public Page<ProcessDefinitionVo> getPagerModel(ProcessDefinitionQueryVo params) ;

    /**
     * 通过流程定义id获取流程定义的信息
     * @param processDefinitionId 流程定义id
     * @return
     */
    public ProcessDefinitionVo getById(String processDefinitionId) ;
}
