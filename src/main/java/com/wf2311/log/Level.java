package com.wf2311.log;

/**
 * @author wf2311
 * @time 2016/12/15 00:12.
 */
public enum Level {
    INSERT("insert"),
    DELETE("delete"),
    UPDATE("update"),
    SELECT("select"),
    LOGIN("login"),
    REGISTER("register"),
    LOGOUT("logout"),
    UNDEFINED("undefined")
    ;
    private String name;

    Level(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
