package cn.zokoo.flow.api;


import cn.zokoo.flow.base.BaseController;
import cn.zokoo.flow.entity.Purchase;
import cn.zokoo.flow.entity.vo.StartProcessInstanceVo;
import cn.zokoo.flow.service.flowable.FlowableProcessInstanceService;
import cn.zokoo.flow.service.leave.PurchaseService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @author : admin
 * @date : 2020-02-09 10:00:54
 * description : 采购Controller
 */
@RestController
@RequestMapping("/api/rest/demo/purchase")
public class PurchaseApi extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(PurchaseApi.class);

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private FlowableProcessInstanceService flowableProcessInstanceService;

    /**
     * 查询报销列表
     * @param purchase
     * @param query
     * @return
     */
    @GetMapping("/getPurchaseList")
    public PagerModel<Purchase> getPurchaseList(Purchase purchase, Query query) {
        PagerModel<Purchase> pm = null;
        try {
            pm = this.purchaseService.getPagerModelByQuery(purchase, query);
        } catch (Exception e) {
            logger.error("PurchaseController-ajaxList:", e);
            e.printStackTrace();
        }
        return pm;
    }

    /**
     * 添加报销申请
     * @param purchase
     * @param sessionId
     * @return
     */
    @PostMapping("/add")
    public ReturnVo add(Purchase purchase, String sessionId) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.FAIL, "添加失败");
        try {
            String purchaseId = UUIDGenerator.generate();
            purchase.setId(purchaseId);
            StartProcessInstanceVo startProcessInstanceVo = new StartProcessInstanceVo();
            startProcessInstanceVo.setBusinessKey(purchaseId);
            User user = SecurityUtils.getCurrentUserObject();
            startProcessInstanceVo.setCreator(user.getId());
            startProcessInstanceVo.setCurrentUserCode(user.getId());
            startProcessInstanceVo.setFormName("采购流程");
            startProcessInstanceVo.setSystemSn("flow");
            startProcessInstanceVo.setProcessDefinitionKey("purchase");
            Map<String, Object> variables = new HashMap<>();
            variables.put("money", purchase.getMoney());
            startProcessInstanceVo.setVariables(variables);
            ReturnVo<ProcessInstance> returnStart = flowableProcessInstanceService.startProcessInstanceByKey(startProcessInstanceVo);
            if (returnStart.getCode().equals(ReturnCode.SUCCESS)){
                String processInstanceId = returnStart.getData().getProcessInstanceId();
                purchase.setProcessInstanceId(processInstanceId);
                this.purchaseService.insertPurchase(purchase);
                returnVo = new ReturnVo(ReturnCode.SUCCESS, "添加成功");
            }else {
                returnVo = new ReturnVo(returnStart.getCode(), returnStart.getMsg());
            }
        }catch (Exception e){
            logger.error("PurchaseController-add:", e);
            e.printStackTrace();
        }

        return returnVo;
    }

    /**
     * 修改报销申请
     * @param purchase
     * @return
     */
    @PostMapping("/update")
    public ReturnVo update(Purchase purchase) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.FAIL, "修改失败");
        try {
            this.purchaseService.updatePurchase(purchase);
            returnVo = new ReturnVo(ReturnCode.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error("PurchaseController-update:", e);
            e.printStackTrace();
        }
        return returnVo;
    }

    /**
     * 批量删除报销申请
     * @param ids
     * @return
     */
    @GetMapping("/dels")
    public ReturnVo dels(String[] ids) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.FAIL, "删除失败");
        try {
            for(String id: ids) {
                this.purchaseService.delPurchaseById(id);
            }
            returnVo = new ReturnVo(ReturnCode.SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error("PurchaseController-del:", e);
            e.printStackTrace();
        }
        return returnVo;
    }
}
