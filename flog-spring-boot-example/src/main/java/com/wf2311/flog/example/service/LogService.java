package com.wf2311.flog.example.service;


import com.alibaba.fastjson.JSON;
import com.wf2311.log.LogInfo;
import com.wf2311.log.LogRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:wf2311@163.com">wf2311</a>
 * @date 2018/5/16 10:02.
 */
@Service
@Slf4j
public class LogService implements LogRecord {
    /**
     * 保存日志信息
     *
     * @param info
     */
    @Override
    public void saveLog(LogInfo info) {
        System.out.println(JSON.toJSONStringWithDateFormat(info,"yyyyMMddHHmmss"));
    }
}
