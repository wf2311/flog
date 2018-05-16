package com.wf2311.log;

import org.aspectj.lang.annotation.Aspect;

import java.util.Collections;
import java.util.List;

/**
 * @author wf2311
 * @date  2017/04/17 13:03.
 */
@Aspect
public class DefaultLogAspect extends AbstractLogAspect {
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
        return Collections.emptyList();
    }

    /**
     * 要指定为特别等级的异常
     */
    @Override
    public List<ExceptionLevel> specifiedLevelExceptions() {
        return Collections.emptyList();
    }
}
