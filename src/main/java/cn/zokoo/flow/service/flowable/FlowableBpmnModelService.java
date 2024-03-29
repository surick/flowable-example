package cn.zokoo.flow.service.flowable;

import org.flowable.bpmn.model.Activity;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.FlowNode;

import java.util.List;

/**
 * @author : june
 * @title: : IFlowableBpmnModelService
 * @projectName : flowable
 * @description: BpmnModelservice
 * @date : 2020/02/2519:38
 */
public interface FlowableBpmnModelService {

    /**
     * 通过流程定义id获取BpmnModel
     *
     * @param processDefId 流程定义id
     * @return
     */
    public BpmnModel getBpmnModelByProcessDefId(String processDefId);

    /**
     * 通过流程定义id获取所有的节点
     *
     * @param processDefId 流程定义id
     * @return
     */
    public List<FlowNode> findFlowNodes(String processDefId);

    /**
     * 获取end节点
     *
     * @param processDefId 流程定义id
     * @return FlowElement
     */
    public List<EndEvent> findEndFlowElement(String processDefId);

    /**
     * 判断节点是不是子流程的节点
     * @param processDefId 流程定义id
     * @param activityId 节点id
     * @return
     */
    public boolean checkActivitySubprocessByActivityId(String processDefId, String activityId);
    /**
     * 通过流程id获取节点
     *
     * @param processDefId 流程定义id
     * @param activityId   节点id
     * @return
     */
    public List<Activity> findActivityByActivityId(String processDefId, String activityId);

    /**
     * 通过流程id获取主流程中的节点
     *
     * @param processDefId 流程定义id
     * @param activityId   节点id
     * @param processDefId
     * @param activityId
     * @return
     */
    public FlowNode findMainProcessActivityByActivityId(String processDefId, String activityId);

    /**
     * 查找节点
     * @param processDefId 流程定义id
     * @param activityId 节点id
     * @return
     */
    public FlowNode findFlowNodeByActivityId(String processDefId, String activityId) ;

    /**
     * 通过名称获取节点
     *
     * @param processDefId 流程定义id
     * @param name         节点名称
     * @return
     */
    public Activity findActivityByName(String processDefId, String name);
}
