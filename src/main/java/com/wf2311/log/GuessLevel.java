/**/
package com.wf2311.log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wf2311
 * @time 2016/12/15 00:20.
 */
public class GuessLevel {
    /**
     * FIXME: 后续要改为可配置读写
     */
    private static List<String> insert = Arrays.asList("insert", "add", "new", "relate", "relation");
    private static List<String> delete = Arrays.asList("delete", "remove");
    private static List<String> update = Arrays.asList("update", "modify", "modified", "set");
    private static List<String> select = Arrays.asList("select", "get", "list", "fizzy", "blur", "show", "is", "has", "check");
    private static List<String> login = Arrays.asList("login");
    private static List<String> register = Arrays.asList("register");
    private static List<String> logout = Arrays.asList("logout");

    private static Map<Level, List<String>> listMap = new HashMap<>(8);

    static {
        listMap.put(Level.INSERT, insert);
        listMap.put(Level.DELETE, delete);
        listMap.put(Level.UPDATE, update);
        listMap.put(Level.SELECT, select);
        listMap.put(Level.LOGIN, login);
        listMap.put(Level.REGISTER, register);
        listMap.put(Level.LOGOUT, login);
    }

    private static boolean match(String methodName, List<String> target) {

        return target.stream().anyMatch(s -> methodName.toLowerCase().startsWith(s.toLowerCase()));
    }

    public static Level guess(String methodName) {
        return listMap.entrySet().stream()
                .filter(e -> match(methodName, e.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().orElse(Level.UNDEFINED);
    }

}
