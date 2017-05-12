package com.wf2311.log;

import com.wf2311.log.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.NANOS;

/**
 * 日志切面处理类
 *
 * @author wf2311
 * @date 2016/06/03 17:11.
 */
public abstract class LogAspect {
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

    protected LogInfo logInfo;

    protected Method method;

    private void init() {
        logInfo = new LogInfo();
        startTime = null;
        endTime = null;
    }

    protected boolean guessLevel = true;

    /**
     * 忽略条件，优先级高于{@code accept()}
     * <p>
     * //FIXME 重写此方法后,无法直接传递给父类的hit()方法
     */
    @Pointcut("@annotation(com.wf2311.log.annotation.LogIgnore)")
    public void ignore() {
    }

    /**
     * 接受条件,优先级低于{@code ignore()}
     * //FIXME 重写此方法后,无法直接传递给父类的hit()方法
     */
    @Pointcut("@annotation(com.wf2311.log.annotation.Log))")
    public void accept() {
    }

    /**
     * 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
     */
    @Pointcut("accept()&&!ignore()")
    protected void hit() {
    }

    /**
     * 配置前置通知,使用在方法aspect()上注册的切入点
     * 同时接受JoinPoint切入点对象,可以没有该参数
     *
     * @param joinPoint the join point
     */
    @Before("hit()")
    public void before(JoinPoint joinPoint) {
//        logger.info("before " + joinPoint);
    }

    /**
     * 配置后置通知,使用在方法aspect()上注册的切入点
     *
     * @param joinPoint the join point
     */
    @After("hit()")
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
    @Around("hit()&&@annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        init();
        startTime = LocalDateTime.now();
        invoke(joinPoint, log);
        /**
         * 方法执行
         */
        return joinPoint.proceed(joinPoint.getArgs());
    }

    @Around("hit()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return around(joinPoint, null);
    }

    /**
     * 配置后置返回通知,使用在方法aspect()上注册的切入点
     */
    @AfterReturning("hit()")
    public void afterReturn(JoinPoint joinPoint) {
        logInfo.setType(Type.NORMAL);
        endTime = LocalDateTime.now();
        saveLog();
    }


    /**
     * After throw.
     */
    @AfterThrowing(pointcut = "hit()", throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Exception e) {
        logInfo.setType(Type.EXCEPTION);
        logInfo.setExceptionCode(e.getClass().getName());
        logInfo.setExceptionDetail(e.getMessage());

        setSpecifiedLevelException(e);
        validateIgnoreException(e);

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
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        this.method = method;

        if (log != null) {
            logInfo.setLevel(log.level());
            logInfo.setDescription(log.value());
        }
        Class clazz = null;
        try {
            clazz = Class.forName(joinPoint.getSignature().getDeclaringTypeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logInfo.setHttpMethod(request.getMethod());
        //请求的IP
        String ip = IpUtil.getIpAddr(request);
        //获取参数列表
        logInfo.setIp(ip);
        logInfo.setMethod(clazz.getName() + "." + method.getName() + "()");
        logInfo.setUrl(request.getRequestURI());
        if (request.getParameterMap() != null) {
            logInfo.setParams(request.getParameterMap());
        } else {
            logInfo.setParams(paramsMap(method, joinPoint));
        }
        if (guessLevel) {
            logInfo.setLevel(GuessLevel.guess(method.getName()).toString());
        }
    }

    private Object paramsMap(Method method, ProceedingJoinPoint joinPoint) {
        return paramsMap(method, null, joinPoint);
    }


    private Object paramsMap(Method method, Class clazz, ProceedingJoinPoint joinPoint) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", method.getParameterTypes());
        try {
            params.put("value", resolveArgs(joinPoint.getArgs()));
        } catch (Exception ignore) {
            params.put("value", "UNKNOWN");
        }

        return params;
    }

    protected List resolveArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return Collections.EMPTY_LIST;
        }

        List<Object> list = new ArrayList<>();
        for (Object arg : args) {
            list.add(resolveArg(arg));
        }
        return list;
    }

    private Object resolveArg(Object arg) {
        if (arg instanceof MultipartRequest) {
            return ((MultipartRequest) arg).getFileNames();
        }
        return arg;
    }

    /**
     * 保存日志信息到数据库
     */
    private void saveLog() {
        logInfo.setCreated(LocalDateTime.now());
        logInfo.setStartTime(startTime);
        logInfo.setEndTime(endTime);
        logInfo.setSpend(spendTime());
        modifyLogInfo();
        record.saveLog(logInfo);
    }

    /**
     * 计算方法调用花费时间
     */
    private long spendTime() {
        return startTime.until(endTime, NANOS);
    }

    /**
     * 修记录信息
     */
    public abstract void modifyLogInfo();


    /**
     * 要排除为异常类型的异常
     */
    public abstract List<ExceptionLevel> ignoreExceptions();

    /**
     * 要指定为特别等级的异常
     */
    public abstract List<ExceptionLevel> specifiedLevelExceptions();

    private void validateIgnoreException(Exception e) {
        ExceptionLevel level = searchFromExceptionLevel(ignoreExceptions(), e);
        if (level != null) {
            logInfo.setLevel(level.getLevel());
            logInfo.setType(Type.NORMAL);
        }
    }

    private void setSpecifiedLevelException(Exception e) {
        ExceptionLevel level = searchFromExceptionLevel(specifiedLevelExceptions(), e);
        if (level != null) {
            logInfo.setLevel(level.getLevel());
        }
    }

    private ExceptionLevel searchFromExceptionLevel(List<ExceptionLevel> exceptionLevels, Exception e) {
        if (exceptionLevels == null || exceptionLevels.size() == 0) {
            return null;
        }
        for (ExceptionLevel level : exceptionLevels) {
            Class<? extends Exception> exceptionType = level.getException();
            if (e.getClass().equals(exceptionType)) {
                return level;
            }
        }
        return null;
    }

}
