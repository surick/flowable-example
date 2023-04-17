package cn.zokoo.flow.controller;

import cn.zokoo.flow.base.BaseController;
import cn.zokoo.flow.entity.Leave;
import cn.zokoo.flow.entity.vo.*;
import cn.zokoo.flow.service.flowable.FlowableProcessInstanceService;
import cn.zokoo.flow.service.flowable.FlowableTaskService;
import cn.zokoo.flow.service.leave.LeaveService;
import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : june
 * @title: : ApiFlowableTaskResource
 * @projectName : flowable
 * @description: 任务列表
 * @date : 2020/02/21 15:34
 */
@RestController
@RequestMapping("/rest/task")
public class FlowableTaskController extends BaseController {

    @Autowired
    private FlowableTaskService flowableTaskService;
    @Autowired
    private FlowableProcessInstanceService flowableProcessInstanceService;
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private HistoryService historyService;

    /**
     * 获取待办任务列表
     *
     * @param params 参数
     * @param query  查询条件
     * @return
     */
    @GetMapping(value = "/get-applying-tasks")
    public PagerModel<TaskVo> getApplyingTasks(TaskQueryVo params, Query query) {
        params.setUserCode(this.getLoginUser().getId());
        PagerModel<TaskVo> pm = flowableTaskService.getApplyingTasks(params, query);
        return pm;
    }

    /**
     * 获取已办任务列表
     *
     * @param params 参数
     * @param query  查询条件
     * @return
     */
    @GetMapping(value = "/get-applyed-tasks")
    public PagerModel<TaskVo> getApplyedTasks(TaskQueryVo params, Query query) {
        params.setUserCode(this.getLoginUser().getId());
        PagerModel<TaskVo> pm = flowableTaskService.getApplyedTasks(params, query);
        return pm;
    }

    /**
     * 获取我发起的流程实例
     *
     * @param params 参数
     * @param query  查询条件
     * @return
     */
    @GetMapping(value = "/my-processInstances")
    public PagerModel<ProcessInstanceVo> myProcessInstances(ProcessInstanceQueryVo params, Query query) {
        params.setUserCode(this.getLoginUser().getId());
        PagerModel<ProcessInstanceVo> pm = flowableProcessInstanceService.getMyProcessInstances(params, query);
        return pm;
    }



    /**
     * 查询表单详情
     * @param params 参数
     * @return
     */
    @PostMapping(value = "/find-formInfo")
    public ReturnVo<FormInfoVo> findFormInfoByFormInfoQueryVo(FormInfoQueryVo params) throws Exception{
        ReturnVo<FormInfoVo> returnVo = new ReturnVo<>(ReturnCode.SUCCESS,"OK");
        FormInfoVo<Leave> formInfoVo = new FormInfoVo(params.getTaskId(),params.getProcessInstanceId());
        String processInstanceId = params.getProcessInstanceId();
        String businessKey = null;
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null){
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            businessKey = historicProcessInstance.getBusinessKey();
        }else {
            businessKey = processInstance.getBusinessKey();
        }
        Leave leave = leaveService.getLeaveById(businessKey);
        formInfoVo.setFormInfo(leave);
        returnVo.setData(formInfoVo);
        return returnVo;
    }

}
