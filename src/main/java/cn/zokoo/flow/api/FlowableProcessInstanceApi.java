package cn.zokoo.flow.api;

import cn.zokoo.flow.base.BaseController;
import cn.zokoo.flow.entity.vo.EndProcessVo;
import cn.zokoo.flow.entity.vo.ProcessInstanceQueryVo;
import cn.zokoo.flow.entity.vo.ProcessInstanceVo;
import cn.zokoo.flow.service.flowable.FlowableProcessInstanceService;
import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : june
 * @title: : ApiTask
 * @projectName : flowable
 * @description: 流程实例API
 * @date : 2020/02/13 21:21
 */
@RestController
@RequestMapping("/api/rest/processInstance")
public class FlowableProcessInstanceApi extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowableProcessInstanceApi.class);
    @Autowired
    private FlowableProcessInstanceService flowableProcessInstanceService;

    /**
     * 分页查询流程实例列表
     *
     * @param params 参数
     * @param query  分页
     * @return
     */
    @PostMapping(value = "/getProcessInstancePage")
    public PagerModel<ProcessInstanceVo> getProcessInstancePage(ProcessInstanceQueryVo params, Query query) {
        PagerModel<ProcessInstanceVo> pm = flowableProcessInstanceService.getPagerModel(params, query);
        return pm;
    }

    /**
     * 删除流程实例
     *
     * @param processInstanceId 参数
     * @return
     */
    @GetMapping(value = "/deleteProcessInstanceById/{processInstanceId}")
    public ReturnVo<String> deleteProcessInstanceById(@PathVariable String processInstanceId) {
        ReturnVo<String> data = flowableProcessInstanceService.deleteProcessInstanceById(processInstanceId);
        return data;
    }

    /**
     * 激活或者挂起流程定义
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/saProcessInstanceById")
    public ReturnVo<String> saProcessInstanceById(String id, int suspensionState) {
        ReturnVo<String> returnVo = flowableProcessInstanceService.suspendOrActivateProcessInstanceById(id, suspensionState);
        return returnVo;
    }

    /**
     * 终止
     *
     * @param params 参数
     * @return
     */
    @PostMapping(value = "/stopProcess")
    public ReturnVo<String> stopProcess(EndProcessVo params) {
        boolean flag = this.isSuspended(params.getProcessInstanceId());
        ReturnVo<String> returnVo = null;
        if (flag){
            params.setMessage("后台执行终止");
            params.setUserCode(this.getLoginUser().getId());
            returnVo = flowableProcessInstanceService.stopProcessInstanceById(params);
        }else{
            returnVo = new ReturnVo<>(ReturnCode.FAIL,"流程已挂起，请联系管理员激活!");
        }
        return returnVo;
    }

}
