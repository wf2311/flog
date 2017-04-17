package com.wf2311.log;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangfeng
 * @time 2017/04/17 13:03.
 */
@Aspect //声明这是一个组件
@Component  //声明这是一个切面Bean
public class DefalutLogAspect extends LogAspect {
    /**
     * 修记录信息
     */
    @Override
    public void modifyLogInfo() {
    }

    /**
     * 要排除为异常类型的异常
     */
    @Override
    public List<ExceptionLevel> ignoreExceptions() {
        return null;
    }

    /**
     * 要指定为特别等级的异常
     */
    @Override
    public List<ExceptionLevel> specifiedLevelExceptions() {
        return null;
    }
}
