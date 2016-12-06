package com.wf2311.log;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Log info.
 *
 * @author wf2311
 * @date 2016 /06/03 17:26.
 */
public class LogInfo implements Serializable{
    /**
     * 创建时间
     */
    private Date created;
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
    private int type;

    /**
     * 日志级别
     */
    private int grade;
    /**
     * 调用方法时的参数相关信息
     */
    private String params;
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
    private Date startTime;
    /**
     * 接口调用结束时间
     */
    private Date endTime;
    /**
     * 接口调用花费用时
     */
    private Long spend;

    /**
     * 请求方式(GET、POST、PUT、DELETE)
     */
    private String httpMethod;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Sets created.
     *
     * @param created the created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Gets creator.
     *
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Sets creator.
     *
     * @param creator the creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets method.
     *
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets method.
     *
     * @param method the method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Sets grade.
     *
     * @param grade the grade
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * Gets params.
     *
     * @return the params
     */
    public String getParams() {
        return params;
    }

    /**
     * Sets params.
     *
     * @param params the params
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * Gets ip.
     *
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets ip.
     *
     * @param ip the ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Gets exception code.
     *
     * @return the exception code
     */
    public String getExceptionCode() {
        return exceptionCode;
    }

    /**
     * Sets exception code.
     *
     * @param exceptionCode the exception code
     */
    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    /**
     * Gets exception detail.
     *
     * @return the exception detail
     */
    public String getExceptionDetail() {
        return exceptionDetail;
    }

    /**
     * Sets exception detail.
     *
     * @param exceptionDetail the exception detail
     */
    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets spend.
     *
     * @return the spend
     */
    public Long getSpend() {
        return spend;
    }

    /**
     * Sets spend.
     *
     * @param spend the spend
     */
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
