package cn.zokoo.flow.mapper.flowable;

import cn.zokoo.flow.entity.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : june
 * @projectName : flowable
 * @description: 流程备注Dao
 * @date : 2020/02/24 13:00
 */
@Mapper
@Repository
public interface FlowableCommentMapper {

    /**
     * 通过流程实例id获取审批意见列表
     * @param ProcessInstanceId 流程实例id
     * @return
     */
    public List<CommentVo> getFlowCommentVosByProcessInstanceId(String ProcessInstanceId);

}
