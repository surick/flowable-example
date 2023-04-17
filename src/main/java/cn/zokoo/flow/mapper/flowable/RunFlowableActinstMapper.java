package cn.zokoo.flow.mapper.flowable;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : june
 * @projectName : flowable
 * @description: 运行时的节点Dao
 * @date : 2020/02/4 17:55
 */
@Mapper
@Repository
public interface RunFlowableActinstMapper {

    /**
     * 删除节点信息
     * @param ids ids
     */
    public void deleteRunActinstsByIds(List<String> ids) ;
}
