package com.wf2311.flog.example.aop;

import com.wf2311.log.ExceptionLevel;
import com.wf2311.log.LogAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangfeng
 * @time 2018/02/07 11:17.
 */
@Aspect
@Order(10)
@Component
public class AccessLogAspect  extends LogAspect {
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
