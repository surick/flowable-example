package cn.zokoo.flow.flowable.interceptor;

import org.flowable.common.engine.impl.interceptor.AbstractCommandInterceptor;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandConfig;
import org.flowable.engine.impl.cmd.CompleteTaskCmd;

/**
 * @author : june
 * @title: : FlowablePostCommandInterceptor
 * @projectName : flowable
 * @description: 后置拦截器
 * @date : 2020/02/1216:11
 */
public class FlowablePostCommandInterceptor extends AbstractCommandInterceptor {

    @Override
    public <T> T execute(CommandConfig commandConfig, Command<T> command) {
        if (command instanceof CompleteTaskCmd){
            CompleteTaskCmd cmd = (CompleteTaskCmd)command;
        }
        return next.execute(commandConfig, command);
    }
}
