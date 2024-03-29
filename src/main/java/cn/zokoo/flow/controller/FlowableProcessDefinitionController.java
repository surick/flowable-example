package cn.zokoo.flow.controller;

import cn.zokoo.flow.base.BaseController;
import cn.zokoo.flow.entity.vo.ProcessDefinitionQueryVo;
import cn.zokoo.flow.entity.vo.ProcessDefinitionVo;
import cn.zokoo.flow.service.flowable.FlowableProcessDefinitionService;
import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import org.flowable.common.engine.impl.util.IoUtil;
import org.flowable.engine.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @author : june
 * @title: : ApiTask
 * @projectName : flowable
 * @description: 模型API
 * @date : 2020/02/13 21:21
 */
@RestController
@RequestMapping("/rest/definition")
public class FlowableProcessDefinitionController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowableProcessDefinitionController.class);
    @Autowired
    private FlowableProcessDefinitionService flowableProcessDefinitionService;
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 分页查询流程定义列表
     *
     * @param params 参数
     * @param query  分页
     * @return
     */
    @PostMapping(value = "/getDefinitionPage")
    public PagerModel<ProcessDefinitionVo> getDefinitionPage(ProcessDefinitionQueryVo params, Query query) {
        PagerModel<ProcessDefinitionVo> pm = flowableProcessDefinitionService.getPagerModel(params, query);
        return pm;
    }

    /**
     * 删除流程定义
     *
     * @param deploymentId 部署id
     */
    @PostMapping(value = "/deleteDeployment")
    public ReturnVo<String> deleteDeployment(String deploymentId) {
        ReturnVo<String> returnVo = new ReturnVo<>(ReturnCode.SUCCESS, "OK");
        repositoryService.deleteDeployment(deploymentId, true);
        return returnVo;
    }

    /**
     * 通过id和类型获取图片
     *
     * @param id       定义id
     * @param type     类型
     * @param response response
     */
    @GetMapping(value = "/processFile/{type}/{id}")
    public void processFile(@PathVariable String id, @PathVariable String type, HttpServletResponse response) {
        try {
            byte[] b = null;
            ProcessDefinitionVo pd = flowableProcessDefinitionService.getById(id);
            if (pd != null) {
                if (type.equals("xml")) {
                    response.setHeader("Content-type", "text/xml;charset=UTF-8");
                    InputStream inputStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getResourceName());
                    b = IoUtil.readInputStream(inputStream, "image inputStream name");
                } else {
                    response.setHeader("Content-Type", "image/png");
                    InputStream inputStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getDgrmResourceName());
                    b = IoUtil.readInputStream(inputStream, "image inputStream name");
                }
                response.getOutputStream().write(b);
            }
        } catch (Exception e) {
            LOGGER.error("ApiFlowableModelResource-loadXmlByModelId:" + e);
            e.printStackTrace();
        }
    }

    /**
     * 激活或者挂起流程定义
     * @param id
     * @return
     */
    @PostMapping(value = "/saDefinitionById")
    public ReturnVo<String> saDefinitionById(String id,int suspensionState) {
        ReturnVo<String> returnVo = returnVo = flowableProcessDefinitionService.suspendOrActivateProcessDefinitionById(id,suspensionState);
        return returnVo;
    }
}
