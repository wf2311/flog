package com.wf2311.log;

import com.wf2311.log.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoUnit.NANOS;

/**
 * 日志切面处理类
 *
 * @author wf2311
 * @date 2016/06/03 17:11.
 */
@Aspect //声明这是一个组件
@Component  //声明这是一个切面Bean
public class LogAspect {
    /**
     * 方法开始执行时间
     */
    private LocalDateTime startTime;
    /**
     * 方法执行结束时间
     */
    private LocalDateTime endTime;

    /**
     * 日志切入点
     */
    @Resource
    private Record record;

    private LogInfo logInfo;

    private void init() {
        logInfo = new LogInfo();
        startTime = null;
        endTime = null;
    }

    /**
     * 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
     */
    @Pointcut("@annotation(com.wf2311.log.annotation.Log))")
    public void aspect() {
    }

    /**
     * 配置前置通知,使用在方法aspect()上注册的切入点
     * 同时接受JoinPoint切入点对象,可以没有该参数
     *
     * @param joinPoint the join point
     */
    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
//        logger.info("before " + joinPoint);
    }

    /**
     * 配置后置通知,使用在方法aspect()上注册的切入点
     *
     * @param joinPoint the join point
     */
    @After("aspect()")
    public void after(JoinPoint joinPoint) {
//        logger.info("after " + joinPoint);
        endTime = LocalDateTime.now();
    }


    /**
     * 配置环绕通知,使用在方法aspect()上注册的切入点<br/><br/>
     * 输入请求的对象信息:如：方法参数列表信息、类名称等<br/><br/>
     * 注意：参数必须使用ProceedingJoinPoint，否则被拦截的控制台方法将不执行.<br/><br/>
     * 只有环绕通知才可以使用JoinPoint的子类ProceedingJoinPoint.<br/><br/>
     * 环绕方法通知要注意必须给出调用之后的返回值，否则被代理的方法会停止调用并返回null<br/><br/>
     *
     * @param joinPoint 连接点
     * @param log       the log
     * @return 方法执行结果 object
     * @throws Throwable 调用出错
     */
    @Around("aspect()&&@annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        init();
        startTime = LocalDateTime.now();
        invoke(joinPoint, log);
        /**
         * 方法执行
         */
        return joinPoint.proceed(joinPoint.getArgs());
    }

    /**
     * 配置后置返回通知,使用在方法aspect()上注册的切入点
     */
    @AfterReturning("aspect()")
    public void afterReturn(JoinPoint joinPoint) {
        logInfo.setType(Type.NORMAL.ordinal());
//        logger.info("afterReturn " + joinPoint);
        endTime = LocalDateTime.now();
        saveLog();
    }


    /**
     * After throw.
     *
     * @param joinPoint the join point
     * @param e         the e
     */
    @AfterThrowing(pointcut = "aspect()", throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Exception e) {
//        logger.info("afterThrow " + joinPoint + "\t" + e.getMessage());
        logInfo.setType(Type.EXCEPTION.ordinal());
        logInfo.setExceptionCode(e.getClass().getName());
        endTime = LocalDateTime.now();
        saveLog();
    }

    /**
     * 获取当前执行的方法
     *
     * @param joinPoint  连接点
     * @param methodName 方法名称
     * @return 方法
     */
    private Method currentMethod(ProceedingJoinPoint joinPoint, String methodName) {
        /**
         * 获取目标类的所有方法，找到当前要执行的方法
         */
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }

    /**
     * 根据jointPoint获取部分调用方法的信息
     *
     * @param joinPoint
     */
    private void invoke(ProceedingJoinPoint joinPoint, Log log) {
//        logger.info("around " + joinPoint);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        logInfo.setGrade(log.grade().ordinal());
        logInfo.setDescription(log.value());
        Class clazz = null;
        try {
            clazz = Class.forName(joinPoint.getSignature().getDeclaringTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //请求的IP
        String ip = IpUtil.getIpAddr(request);
        //获取参数列表
        logInfo.setIp(ip);
        logInfo.setMethod(clazz.getName() + "." + method.getName() + "()");
        logInfo.setParams(paramsMap(method, joinPoint));
    }

    private Object paramsMap(Method method, ProceedingJoinPoint joinPoint) {
        return paramsMap(method, null, joinPoint);
    }

    private Object paramsMap(Method method, Class clazz, ProceedingJoinPoint joinPoint) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", method.getParameterTypes());
//        try {
//            jsonObject.put("name", ReflectUtils.getInstance().getMethodParameterNames(method.getName(), clazz, method.getParameterTypes()));
//        } catch (Exception e) {
//            jsonObject.put("name", "UNKNOWN");
//        }
        params.put("value", joinPoint.getArgs());

        return params;
    }

    /**
     * 保存日志信息到数据库
     */
    private void saveLog() {
        logInfo.setCreated(LocalDateTime.now());
        logInfo.setStartTime(startTime);
        logInfo.setEndTime(endTime);
        logInfo.setSpend(spendTime());
        record.saveLog(logInfo);
    }

    /**
     * 计算方法调用花费时间
     */
    private long spendTime() {
        return endTime.until(startTime, NANOS);
    }

//    /**
//     * 设置日志信息
//     */
//    public void setRecord(Record record) {
//        this.record = record;
//    }

}
