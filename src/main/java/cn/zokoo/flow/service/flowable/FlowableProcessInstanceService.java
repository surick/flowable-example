package cn.zokoo.flow.service.flowable;

import cn.zokoo.flow.entity.vo.RevokeProcessVo;
import cn.zokoo.flow.entity.vo.EndProcessVo;
import cn.zokoo.flow.entity.vo.ProcessInstanceQueryVo;
import cn.zokoo.flow.entity.vo.StartProcessInstanceVo;
import cn.zokoo.flow.entity.vo.ProcessInstanceVo;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import org.flowable.engine.runtime.ProcessInstance;

/**
 * @author : june
 * @projectName : flowable
 * @description: 流程实例service
 * @date : 2020/020/2511:40
 */
public interface FlowableProcessInstanceService {

    /**
     * 启动流程
     *
     * @param startProcessInstanceVo 参数
     * @return
     */
    public ReturnVo<ProcessInstance> startProcessInstanceByKey(StartProcessInstanceVo startProcessInstanceVo);

    /**
     * 查询流程实例列表
     *
     * @param params 参数
     * @param query  分页参数
     * @return
     */
    public PagerModel<ProcessInstanceVo> getPagerModel(ProcessInstanceQueryVo params, Query query);

    /**
     * 查询我发起的流程实例
     *
     * @param params 参数
     * @param query  分页参数
     * @return
     */
    public PagerModel<ProcessInstanceVo> getMyProcessInstances(ProcessInstanceQueryVo params, Query query);

    /**
     * 获取流程图图片
     *
     * @param processInstanceId 流程实例id
     * @return
     */
    public byte[] createImage(String processInstanceId);

    /**
     * 删除流程实例
     *
     * @param processInstanceId 流程实例id
     * @return
     */
    public ReturnVo<String> deleteProcessInstanceById(String processInstanceId);

    /**
     * 激活流程定义
     *
     * @param processInstanceId 流程实例id
     * @param suspensionState   2激活 1挂起
     */
    public ReturnVo<String> suspendOrActivateProcessInstanceById(String processInstanceId, Integer suspensionState);

    /**
     * 终止流程
     * @param endVo 参数
     * @return
     */
    public ReturnVo<String> stopProcessInstanceById(EndProcessVo endVo) ;

    /**
     * 撤回流程
     * @param revokeVo 参数
     * @return
     */
    public ReturnVo<String> revokeProcess(RevokeProcessVo revokeVo);
}
