package com.wf2311.flog.store;

import com.wf2311.log.LogInfo;
import com.wf2311.log.Record;
import jodd.db.oom.DbOomQuery;

/**
 * @author wf2311
 */
public class LogService implements Record{
    @Override
    public void saveLog(LogInfo info) {
        DbOomQuery.query("");
    }
}
