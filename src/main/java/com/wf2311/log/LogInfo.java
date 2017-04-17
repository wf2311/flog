package com.wf2311.log;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志信息
 *
 * @author wf2311
 * @date 2016/06/03 17:26.
 */
public class LogInfo implements Serializable {
    /**
     * 创建时间
     */
    private LocalDateTime created;
    /**
     * 创建者(json字符串)
     */
    private String creator;

    /**
     * 主键
     */
    private Long id;

    /**
     * 方法描述
     */
    private String description;
    /**
     * 调用方法名
     */
    private String method;
    /**
     * 运行结果类型(正常执行/发生异常)
     */
    private Type type;

    /**
     * 日志级别
     */
    private String level;
    /**
     * 调用方法时的参数相关信息
     */
    private Object params;
    /**
     * 方法请求者ip
     */
    private String ip;
    /**
     * 异常类型
     */
    private String exceptionCode;
    /**
     * 异常信息
     */
    private String exceptionDetail;

    /**
     * 接口调用开始时间
     */
    private LocalDateTime startTime;
    /**
     * 接口调用结束时间
     */
    private LocalDateTime endTime;
    /**
     * 接口调用花费用时(nanos)
     */
    private Long spend;

    /**
     * 请求方式(GET、POST、PUT、DELETE)
     */
    private String httpMethod;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getSpend() {
        return spend;
    }

    public void setSpend(Long spend) {
        this.spend = spend;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}
