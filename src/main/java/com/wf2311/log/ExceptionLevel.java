package com.wf2311.log;

/**
 * @author wf2311
 * @time 2016/12/13 10:16.
 */
public class ExceptionLevel {
    private Class<? extends Exception> exception;
    private String level;

    public ExceptionLevel(Class<? extends Exception> exception, String type) {
        this.exception = exception;
        this.level = type;
    }

    public Class<? extends Exception> getException() {
        return exception;
    }

    public void setException(Class<? extends Exception> exception) {
        this.exception = exception;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
