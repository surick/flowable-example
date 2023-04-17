import cn.zokoo.flow.FlowableApplication;
import cn.zokoo.flow.service.flowable.FlowableTaskService;
import cn.zokoo.flow.entity.vo.TurnTaskVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : june
 * @title: : TaskTest
 * @projectName : flowable
 * @description: TODO
 * @date : 2020/02/510:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableApplication.class)
public class TaskTest {

    @Autowired
    private FlowableTaskService flowableTaskService;

    @Test
    public void testTurnTask() {
        TurnTaskVo turnTaskVo = new TurnTaskVo();
        turnTaskVo.setTaskId("4b48af1616a911eab464dc8b287b3603");
        turnTaskVo.setProcessInstanceId("61cebe8b16a711eab464dc8b287b3603");
        turnTaskVo.setTurnToUserId("00000004");
        turnTaskVo.setMessage("转办");
        flowableTaskService.turnTask(turnTaskVo);
    }
}
