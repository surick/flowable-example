import cn.zokoo.flow.FlowableApplication;
import cn.zokoo.flow.entity.Leave;
import cn.zokoo.flow.service.flowable.FlowableProcessInstanceService;
import cn.zokoo.flow.service.leave.LeaveService;
import cn.zokoo.flow.entity.vo.RevokeProcessVo;
import cn.zokoo.flow.entity.vo.StartProcessInstanceVo;
import com.dragon.tools.common.DateUtil;
import com.dragon.tools.common.UUIDGenerator;
import com.dragon.tools.vo.ReturnVo;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : june
 * @title: : Process
 * @projectName : flowable
 * @description:
 * @date : 2020/02/2118:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableApplication.class)
public class ProcessInstanceTest {

    @Autowired
    private FlowableProcessInstanceService flowableProcessInstanceService;
    @Autowired
    private LeaveService leaveService;

    @Test
    public void testStartProcess() throws Exception{
        Leave leave = new Leave();
        String leaveId = UUIDGenerator.generate();
        leave.setId(leaveId);
        leave.setDays(2);
        leave.setName("刘文军");
        Date date = new Date();
        leave.setStartTime(date);
        leave.setEndTime(DateUtil.addDate(date,1));
        StartProcessInstanceVo startProcessInstanceVo = new StartProcessInstanceVo();
        startProcessInstanceVo.setBusinessKey(leaveId);
        startProcessInstanceVo.setCreator("00000001");
        startProcessInstanceVo.setCurrentUserCode("00000001");
        startProcessInstanceVo.setFormName("请假流程");
        startProcessInstanceVo.setSystemSn("flow");
        startProcessInstanceVo.setProcessDefinitionKey("qingjia");
        Map<String,Object> variables = new HashMap<>();
        variables.put("days",leave.getDays());
        startProcessInstanceVo.setVariables(variables);
        ReturnVo<ProcessInstance> returnStart = flowableProcessInstanceService.startProcessInstanceByKey(startProcessInstanceVo);
        String processInstanceId = returnStart.getData().getProcessInstanceId();
        leave.setProcessInstanceId(processInstanceId);
        this.leaveService.insertLeave(leave);
    }
    @Test
    public void testRevokeProcess() {
        RevokeProcessVo revokeVo = new RevokeProcessVo();
        revokeVo.setProcessInstanceId("021d89c116a011ea89b4dc8b287b3603");
        flowableProcessInstanceService.revokeProcess(revokeVo);
    }
}
