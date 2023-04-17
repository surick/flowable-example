package cn.zokoo.flow;

import cn.zokoo.flow.config.ApplicationConfiguration;
import cn.zokoo.flow.config.DatabaseConfiguration;
import cn.zokoo.flow.constant.FlowConstant;
import cn.zokoo.flow.config.AppDispatcherServletConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : june
 * @title: : FlowManagerApplication
 * @projectName : flowable
 * @description: 启动类
 * @date : 2020/02/13 13:34
 */
@Import({
        ApplicationConfiguration.class,
        AppDispatcherServletConfiguration.class,
        DatabaseConfiguration.class})
@EnableScheduling
@MapperScan(FlowConstant.MAPPER_SCAN)
@EnableTransactionManagement
//@SpringBootApplication(scanBasePackages = {"cn.zokoo"})
@ComponentScan(basePackages = {"cn.zokoo.*"})
// 移除 Security 自动配置
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class FlowableApplication {
    private static final Logger logger = LoggerFactory.getLogger(FlowableApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
        logger.info("##########################zk-flowable启动成功############################");
    }
}
