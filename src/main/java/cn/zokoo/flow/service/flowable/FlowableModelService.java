package cn.zokoo.flow.service.flowable;

import cn.zokoo.flow.entity.vo.ModelVo;
import com.dragon.tools.vo.ReturnVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : june
 * @projectName : flowable
 * @description: 模型service
 * @date : 2020/02/1920:56
 */
public interface FlowableModelService {

    /**
     * 导入模型
     * @param file 文件
     * @return
     */
    public ReturnVo<String> importProcessModel(MultipartFile file);

    /**
     * 添加模型
     * @param modelVo
     * @return
     */
    public ReturnVo<String> addModel(ModelVo modelVo);
}
