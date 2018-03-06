package com.wf2311.flog.example.service;

import com.alibaba.fastjson.JSON;
import com.wf2311.log.LogInfo;
import com.wf2311.log.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wangfeng
 * @time 2018/02/07 11:20.
 */
@Service
@Slf4j
public class AccessLogService implements Record {
    /**
     * 保存日志信息
     *
     * @param info
     */
    @Override
    public void saveLog(LogInfo info) {
        log.info(JSON.toJSONString(info));
    }
}
