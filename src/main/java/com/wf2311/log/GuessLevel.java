/**/
package com.wf2311.log;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author wf2311
 * @time 2016/12/15 00:20.
 */
public class GuessLevel {
    /**
     * FIXME: 后续要改为可配置读写
     */
    private static List<String> insert = Arrays.asList("insert", "add", "new","relate","relation");
    private static List<String> delete = Arrays.asList("delete", "remove");
    private static List<String> update = Arrays.asList("update", "modify", "modified", "set");
    private static List<String> select = Arrays.asList("select", "get", "list", "fizzy", "blur", "show", "is", "has","check");
    private static List<String> login = Arrays.asList("login");
    private static List<String> register = Arrays.asList("register");
    private static List<String> logout = Arrays.asList("logout");

    private static boolean match(String methodName, List<String> target) {
        for (String s : target) {
            if (methodName.toLowerCase().startsWith(s.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static Level guess(String methodName) {
        if (match(methodName, insert)) {
            return Level.INSERT;
        }

        if (match(methodName, delete)) {
            return Level.DELETE;
        }

        if (match(methodName, update)) {
            return Level.UPDATE;
        }

        if (match(methodName, select)) {
            return Level.SELECT;
        }

        if (match(methodName, login)) {
            return Level.LOGIN;
        }

        if (match(methodName, register)) {
            return Level.REGISTER;
        }

        if (match(methodName, logout)) {
            return Level.LOGOUT;
        }
        return Level.UNDEFINED;

    }

}
