package cn.zokoo.flow.config;

import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.spring.SpringIdmEngineConfiguration;
import org.flowable.idm.spring.authentication.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: 密码加密策略
 * @Author: june
 * @Since:15:16 2020/02/11
 */
@Configuration
public class IdmProcessEngineConfiguration extends SpringIdmEngineConfiguration {

    @Bean
    public PasswordEncoder bCryptEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringEncoder passwordEncoder(){
        return new SpringEncoder(bCryptEncoder());
    }

    @Override
    public IdmEngineConfiguration setPasswordEncoder(org.flowable.idm.api.PasswordEncoder passwordEncoder) {
        return super.setPasswordEncoder(passwordEncoder());
    }
}
