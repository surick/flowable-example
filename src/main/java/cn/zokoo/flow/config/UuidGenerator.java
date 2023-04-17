package cn.zokoo.flow.config;

import org.flowable.common.engine.impl.persistence.StrongUuidGenerator;

/**
 * @Description:
 * @Author: june
 * @Since:13:26 2020/02/8
 */
public class UuidGenerator extends StrongUuidGenerator {

    @Override
    public String getNextId() {
        String uuid = super.getNextId();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

}
