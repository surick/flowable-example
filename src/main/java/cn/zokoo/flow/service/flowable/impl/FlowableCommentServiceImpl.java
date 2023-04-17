package cn.zokoo.flow.service.flowable.impl;

import cn.zokoo.flow.entity.vo.CommentVo;
import cn.zokoo.flow.mapper.flowable.FlowableCommentMapper;
import cn.zokoo.flow.cmd.AddHisCommentCmd;
import cn.zokoo.flow.enm.CommentTypeEnum;
import cn.zokoo.flow.service.flowable.FlowableCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : june
 * @title: : FlowCommentServiceImpl
 * @projectName : flowable
 * @description: 流程备注service
 * @date : 2020/02/2412:58
 */
@Service
public class FlowableCommentServiceImpl extends BaseProcessService implements FlowableCommentService {

    @Autowired
    private FlowableCommentMapper flowableCommentMapper;

    @Override
    public void addComment(CommentVo comment) {
        managementService.executeCommand(new AddHisCommentCmd(comment.getTaskId(), comment.getUserId(), comment.getProcessInstanceId(),
                comment.getType(), comment.getMessage()));
    }

    @Override
    public List<CommentVo> getFlowCommentVosByProcessInstanceId(String processInstanceId) {
        List<CommentVo> datas = flowableCommentMapper.getFlowCommentVosByProcessInstanceId(processInstanceId);
        datas.forEach(commentVo -> {
            commentVo.setTypeName(CommentTypeEnum.getEnumMsgByType(commentVo.getType()));
        });
        return datas;
    }
}
