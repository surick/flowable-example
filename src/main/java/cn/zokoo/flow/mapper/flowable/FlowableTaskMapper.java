package cn.zokoo.flow.mapper.flowable;

import cn.zokoo.flow.entity.vo.TaskQueryVo;
import cn.zokoo.flow.entity.vo.TaskVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author : june
 * @title: : IFlowableTaskDao
 * @projectName : flowable
 * @description: flowabletask的查询
 * @date : 2020/02/23 16:34
 */
@Mapper
@Repository
public interface FlowableTaskMapper {
    /**
     * 查询待办任务
     * @param params 参数
     * @return
     */
    public Page<TaskVo> getApplyingTasks(TaskQueryVo params) ;

    /**
     * 查询已办任务列表
     * @param params 参数
     * @return
     */
    public Page<TaskVo> getApplyedTasks(TaskQueryVo params) ;

}
