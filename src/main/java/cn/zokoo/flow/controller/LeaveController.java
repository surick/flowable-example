package cn.zokoo.flow.controller;

import cn.zokoo.flow.base.BaseController;
import cn.zokoo.flow.entity.Leave;
import cn.zokoo.flow.entity.vo.StartProcessInstanceVo;
import cn.zokoo.flow.service.flowable.FlowableProcessInstanceService;
import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.common.UUIDGenerator;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.User;
import org.flowable.ui.common.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author : admin
 * @date : 2020-02-20 19:06:48
 * description : 请假单Controller
 */
@RestController
@RequestMapping("/rest/leave")
public class LeaveController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LeaveController.class);

    private final String nameSpace = "leave";

    @Autowired
    private cn.zokoo.flow.service.leave.LeaveService LeaveService;
    @Autowired
    private FlowableProcessInstanceService flowableProcessInstanceService;

    /**
     * 查询请假列表
     * @param Leave
     * @param query
     * @param sessionId
     * @return
     */
    @GetMapping("/getLeaveList")
    public PagerModel<Leave> getLeaveList(Leave Leave, Query query, String sessionId) {
        PagerModel<Leave> pm = null;
        try {
            pm = this.LeaveService.getPagerModelByQuery(Leave, query);
        } catch (Exception e) {
            logger.error("LeaveController-ajaxList:", e);
            e.printStackTrace();
        }
        return pm;
    }

    /**
     * 添加请假申请
     * @param leave
     * @param sessionId
     * @return
     */
    @PostMapping("/add")
    public ReturnVo add(Leave leave, String sessionId) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.FAIL, "添加失败");
        try {
            String leaveId = UUIDGenerator.generate();
            leave.setId(leaveId);
            StartProcessInstanceVo startProcessInstanceVo = new StartProcessInstanceVo();
            startProcessInstanceVo.setBusinessKey(leaveId);
            User user = SecurityUtils.getCurrentUserObject();
            startProcessInstanceVo.setCreator(user.getId());
            startProcessInstanceVo.setCurrentUserCode(user.getId());
            startProcessInstanceVo.setFormName("请假流程");
            startProcessInstanceVo.setSystemSn("flow");
            startProcessInstanceVo.setProcessDefinitionKey("leave");
            Map<String, Object> variables = new HashMap<>();
            variables.put("days", leave.getDays());
            startProcessInstanceVo.setVariables(variables);
            //设置三个人作为多实例的人员
            List<String> userList = new ArrayList<>();
            userList.add("00000005");
            userList.add("00000006");
            variables.put("userList", userList);

            ReturnVo<ProcessInstance> returnStart = flowableProcessInstanceService.startProcessInstanceByKey(startProcessInstanceVo);
            if (returnStart.getCode().equals(ReturnCode.SUCCESS)){
                String processInstanceId = returnStart.getData().getProcessInstanceId();
                leave.setProcessInstanceId(processInstanceId);
                this.LeaveService.insertLeave(leave);
                returnVo = new ReturnVo(ReturnCode.SUCCESS, "添加成功");
            }else {
                returnVo = new ReturnVo(returnStart.getCode(), returnStart.getMsg());
            }
        } catch (Exception e) {
            logger.error("LeaveController-add:", e);
            e.printStackTrace();
        }
        return returnVo;
    }

    /**
     * 修改请假申请
     * @param Leave
     * @param sessionId
     * @return
     */
    @PostMapping("/update")
    public ReturnVo update(Leave Leave, String sessionId) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.FAIL, "修改失败");
        try {
            this.LeaveService.updateLeave(Leave);
            returnVo = new ReturnVo(ReturnCode.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error("LeaveController-update:", e);
            e.printStackTrace();
        }
        return returnVo;
    }

    /**
     * 更新请假状态
     * @param json
     */
    @PostMapping("/updateLeaveStatus")
    public void updateLeaveStatus(@RequestBody  String json) {
        logger.error("修改状态"+json);
    }

}
