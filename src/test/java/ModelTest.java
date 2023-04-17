import cn.zokoo.flow.FlowableApplication;
import org.flowable.ui.modeler.service.FlowableModelQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author : june
 * @title: : ModelTest
 * @projectName : flowable
 * @description: TODO
 * @date : 2020/02/1414:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableApplication.class)
public class ModelTest {

    @Autowired
    private FlowableModelQueryService flowableModelQueryService;

    @Test
    public void createModel() throws Exception{
        File file = new File("E:\\workspace\\test\\flowabletest\\src\\leave.bpmn");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("leave.bpmn",inputStream);
        flowableModelQueryService.importProcessModel(null,multipartFile);
    }
}
