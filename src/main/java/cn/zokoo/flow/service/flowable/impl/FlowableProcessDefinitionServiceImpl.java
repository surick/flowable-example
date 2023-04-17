package cn.zokoo.flow.service.flowable.impl;

import cn.zokoo.flow.entity.vo.ProcessDefinitionQueryVo;
import cn.zokoo.flow.entity.vo.ProcessDefinitionVo;
import cn.zokoo.flow.mapper.flowable.FlowableProcessDefinitionMapper;
import cn.zokoo.flow.service.flowable.FlowableProcessDefinitionService;
import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : june
 * @title: : FlowableProcessDefinitionServiceImpl
 * @projectName : flowable
 * @description: 流程定义service
 * @date : 2020/02/1314:18
 */
@Service
public class FlowableProcessDefinitionServiceImpl extends BaseProcessService implements FlowableProcessDefinitionService {

    @Autowired
    private FlowableProcessDefinitionMapper flowableProcessDefinitionMapper;

    @Override
    public PagerModel<ProcessDefinitionVo> getPagerModel(ProcessDefinitionQueryVo params, Query query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        Page<ProcessDefinitionVo> page = flowableProcessDefinitionMapper.getPagerModel(params);
        return new PagerModel<>(page);
    }

    @Override
    public ProcessDefinitionVo getById(String processDefinitionId) {
        return flowableProcessDefinitionMapper.getById(processDefinitionId);
    }

    @Override
    public ReturnVo suspendOrActivateProcessDefinitionById(String processDefinitionId,int suspensionState) {
        ReturnVo returnVo = null;
        if (suspensionState == 1){
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
            returnVo = new ReturnVo(ReturnCode.SUCCESS,"挂起成功");
        }else {
            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
            returnVo = new ReturnVo(ReturnCode.SUCCESS,"激活成功");
        }
        return returnVo;
    }

    public void getLine() {

    }

}
