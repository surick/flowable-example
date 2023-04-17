package cn.zokoo.flow.service.flowable;

import cn.zokoo.flow.entity.vo.CommentVo;

import java.util.List;

/**
 * @author : june
 * @projectName : flowable
 * @description: 审批意见service
 * @date : 2020/02/2412:55
 */
public interface FlowableCommentService {

    /**
     * 添加备注
     * @param comment 参数
     */
    public void addComment(CommentVo comment) ;

    /**
     * 通过流程实例id获取审批意见列表
     * @param processInstanceId 流程实例id
     * @return
     */
    public List<CommentVo> getFlowCommentVosByProcessInstanceId(String processInstanceId) ;

}
