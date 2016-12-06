package com.wf2311.log;


/**
 * 日志切入点接口类
 *
 * @author wf2311
 * @date 2016 /06/03 17:08.
 */
public interface Record {

    /**
     * 保存日志信息
     *
     * @param info 日志信息
     */
    void saveLog(LogInfo info);
}
