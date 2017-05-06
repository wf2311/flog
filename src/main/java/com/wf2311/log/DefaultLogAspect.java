package com.wf2311.log;

import java.util.Collections;
import java.util.List;

/**
 * @author wangfeng
 * @time 2017/04/17 13:03.
 */
public class DefaultLogAspect extends LogAspect {
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
