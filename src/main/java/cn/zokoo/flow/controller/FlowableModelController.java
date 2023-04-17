package cn.zokoo.flow.controller;

import cn.zokoo.flow.base.BaseController;
import cn.zokoo.flow.entity.vo.ModelVo;
import cn.zokoo.flow.service.flowable.FlowProcessDiagramGenerator;
import cn.zokoo.flow.service.flowable.FlowableModelService;
import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.vo.ReturnVo;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.idm.api.User;
import org.flowable.ui.common.service.exception.BadRequestException;
import org.flowable.ui.modeler.domain.AbstractModel;
import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * @author : june
 * @title: : ApiTask
 * @projectName : flowable
 * @description: 模型API
 * @date : 2020/02/13 21:21
 */
@RestController
@RequestMapping("/rest/model")
public class FlowableModelController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowableModelController.class);
    @Autowired
    private FlowableModelService flowableModelService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private FlowProcessDiagramGenerator flowProcessDiagramGenerator;
    @Autowired
    private IdentityService identityService;

    /**
     *查询流程模型列表
     * @return
     */
    @GetMapping(value = "/getModelPage")
    public ReturnVo<PagerModel<AbstractModel>> getModelPage() {
        ReturnVo<PagerModel<AbstractModel>> returnVo = new ReturnVo<>(ReturnCode.SUCCESS, "OK");
        List<AbstractModel> datas = modelService.getModelsByModelType(AbstractModel.MODEL_TYPE_BPMN);
        PagerModel<AbstractModel> pm = new PagerModel<>(datas.size(), datas);
        pm.getData().forEach(abstractModel -> {
            User user = identityService.createUserQuery().userId(abstractModel.getCreatedBy()).singleResult();
            abstractModel.setCreatedBy(user.getFirstName());
        });
        returnVo.setData(pm);
        return returnVo;
    }

    /**
     * 新增流程模型
     * @param params
     * @return
     */
    @PostMapping(value = "/addModel")
    public ReturnVo<String> addModel(@RequestBody ModelVo params) {
        ReturnVo<String> returnVo = new ReturnVo<>(ReturnCode.SUCCESS, "OK");
        try {
            flowableModelService.addModel(params);
        }catch (BadRequestException e){
            returnVo = new ReturnVo<>(ReturnCode.FAIL, e.getMessage());
        }

        return returnVo;
    }

    /**
     * 导入流程模型
     * @param file
     * @return
     */
    @PostMapping(value = "/import-process-model")
    public ReturnVo<String> importProcessModel(@RequestParam("file") MultipartFile file) {
        ReturnVo<String> returnVo = new ReturnVo<>(ReturnCode.SUCCESS, "OK");
        try {
            flowableModelService.importProcessModel(file);
        }catch (BadRequestException e){
            returnVo = new ReturnVo<>(ReturnCode.FAIL, e.getMessage());
        }
        return returnVo;
    }

    /**
     * 发布流程模型
     * @param modelId
     * @return
     */
    @PostMapping(value = "/deploy")
    public ReturnVo<String> deploy(String modelId) {
        ReturnVo<String> returnVo = new ReturnVo<>(ReturnCode.FAIL, "部署流程失败！");
        if (StringUtils.isBlank(modelId)) {
            returnVo.setMsg("模板ID不能为空！");
            return returnVo;
        }
        try {
            Model model = modelService.getModel(modelId.trim());
            //到时候需要添加分类
            String categoryCode = "1000";
            BpmnModel bpmnModel = modelService.getBpmnModel(model);
            //添加隔离信息
            String tenantId = "flow";
            //必须指定文件后缀名否则部署不成功
            Deployment deploy = repositoryService.createDeployment()
                    .name(model.getName())
                    .key(model.getKey())
                    .category(categoryCode)
                    .tenantId(tenantId)
                    .addBpmnModel(model.getKey() + ".bpmn", bpmnModel)
                    .deploy();
            returnVo.setData(deploy.getId());
            returnVo.setMsg("部署流程成功！");
            returnVo.setCode(ReturnCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnVo.setMsg(String.format("部署流程异常！- %s", e.getMessage()));
        }
        return returnVo;
    }
    /**
     * 显示流程xml内容
     *
     * @param modelId
     * @return
     */
    @GetMapping(value = "/loadXmlByModelId/{modelId}")
    public void loadXmlByModelId(@PathVariable String modelId, HttpServletResponse response) {
        try {
            Model model = modelService.getModel(modelId);
            byte[] b = modelService.getBpmnXML(model);
            response.setHeader("Content-type", "text/xml;charset=UTF-8");
            response.getOutputStream().write(b);
        } catch (Exception e) {
            LOGGER.error("ApiFlowableModelResource-loadXmlByModelId:" + e);
            e.printStackTrace();
        }
    }

    /**
     * 显示流程图片
     * @param modelId
     * @param response
     */
    @GetMapping(value = "/loadPngByModelId/{modelId}")
    public void loadPngByModelId(@PathVariable String modelId, HttpServletResponse response) {
        Model model = modelService.getModel(modelId);
        BpmnModel bpmnModel = modelService.getBpmnModel(model, new HashMap<>(), new HashMap<>());
        InputStream is = flowProcessDiagramGenerator.generateDiagram(bpmnModel);
        try {
            response.setHeader("Content-Type", "image/png");
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (Exception e) {
            LOGGER.error("ApiFlowableModelResource-loadPngByModelId:" + e);
            e.printStackTrace();
        }
    }


}
