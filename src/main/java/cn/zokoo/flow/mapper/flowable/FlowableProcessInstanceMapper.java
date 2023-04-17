package cn.zokoo.flow.mapper.flowable;

import cn.zokoo.flow.entity.vo.ProcessInstanceQueryVo;
import cn.zokoo.flow.entity.vo.ProcessInstanceVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author : june
 * @title: : IFlowableProcessInstentDao
 * @projectName : flowable
 * @description: TODO
 * @date : 2020/02/23 17:09
 */
@Mapper
@Repository
public interface FlowableProcessInstanceMapper {

    /**
     * 通过条件查询流程实例VO对象列表
     * @param params 参数
     * @return
     */
    public Page<ProcessInstanceVo> getPagerModel(ProcessInstanceQueryVo params);
}
