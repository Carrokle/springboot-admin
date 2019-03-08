package com.lb.aspect;

import com.alibaba.fastjson.JSONObject;
import com.lb.annotation.Log;
import com.lb.entity.OperationLog;
import com.lb.service.IOperationLogService;
import com.lb.service.SpringContextBeanService;
import com.lb.util.ComUtil;
import com.lb.util.JWTUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Date: 2019-2-28
 * Description:
 */
public class RecordLogAspect extends AbstractAspectManager {
   private Logger logger = LoggerFactory.getLogger(RecordLogAspect.class);
    public RecordLogAspect(AspectApi aspectApi) {
        super(aspectApi);
    }


    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        super.doHandlerAspect(pjp,method);
        return execute(pjp,method);
    }

    @Override
    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable {
        Log log = method.getAnnotation(Log.class);
        // 异常日志信息
        String actionLog = null;
        StackTraceElement[] stackTrace = null;
        // 是否执行异常
        boolean isException = false;
        // 开始时间
        long operationTime = System.currentTimeMillis();
        try {
            return pjp.proceed(pjp.getArgs());
        } catch (Throwable throwable) {
            isException = true;
            actionLog = throwable.getMessage();
            stackTrace = throwable.getStackTrace();
            throw throwable;
        } finally {
            // 日志处理
            logHandle( pjp , method , log , actionLog , operationTime , isException,stackTrace );
        }
    }

    private void logHandle (ProceedingJoinPoint joinPoint ,
                            Method method ,
                            Log log ,
                            String actionLog ,
                            long startTime  ,
                            boolean isException,
                            StackTraceElement[] stackTrace) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        IOperationLogService operationLogService = SpringContextBeanService.getBean(IOperationLogService.class);
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String authorization = request.getHeader("Authorization");
        OperationLog operationLog = new OperationLog();
        if(!ComUtil.isEmpty(authorization)){
            String userNo = JWTUtil.getUserNo(authorization);
            operationLog.setUserNo(userNo);
        }
        operationLog.setIp(getIpAddress(request));
        operationLog.setClassName(joinPoint.getTarget().getClass().getName() );
        operationLog.setCreateTime(startTime);
        operationLog.setLogDescription(log.description());
        operationLog.setModelName(log.modelName());
        operationLog.setAction(log.action());
        if(isException){
            StringBuilder sb = new StringBuilder();
            sb.append(actionLog+" &#10; ");
            for (int i = 0; i < stackTrace.length; i++) {
                sb.append(stackTrace[i]+" &#10; ");
            }
            operationLog.setMessage(sb.toString());
        }
        operationLog.setMethodName(method.getName());
        operationLog.setSucceed(isException == true ? 2:1);
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        boolean isJoint = false;
        for (int i = 0; i < args.length; i++) {
            if(args[i] instanceof JSONObject){
                JSONObject parse = (JSONObject)JSONObject.parse(args[i].toString());
                if(!ComUtil.isEmpty(parse.getString("password"))){
                    parse.put("password","*******");
                }
                if(!ComUtil.isEmpty(parse.getString("rePassword"))){
                    parse.put("rePassword","*******");
                }
                if(!ComUtil.isEmpty(parse.getString("oldPassword"))){
                    parse.put("oldPassword","*******");
                }
                operationLog.setActionArgs(parse.toString());
            }else if(args[i] instanceof String
                    || args[i] instanceof Long
                    || args[i] instanceof Integer
                    || args[i] instanceof Double
                    || args[i] instanceof Float
                    || args[i] instanceof Byte
                    || args[i] instanceof Short
                    || args[i] instanceof Character){
                isJoint=true;
            }
            else if(args[i] instanceof String []
                    || args[i] instanceof Long []
                    || args[i] instanceof Integer []
                    || args[i] instanceof Double []
                    || args[i] instanceof Float []
                    || args[i] instanceof Byte []
                    || args[i] instanceof Short []
                    || args[i] instanceof Character []){
                Object[] strs = (Object[])args[i];
                StringBuilder sbArray  =new StringBuilder();
                sbArray.append("[");
                for (Object str:strs) {
                    sbArray.append(str.toString()+",");
                }
                sbArray.deleteCharAt(sbArray.length()-1);
                sbArray.append("]");
                operationLog.setActionArgs(sbArray.toString());
            }else {
                continue;
            }
        }
        if(isJoint){
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (String key:parameterMap.keySet()) {
                String[] strings = parameterMap.get(key);
                for (String str:strings) {
                    sb.append(key+"="+str+"&");
                }
            }
            operationLog.setActionArgs(sb.deleteCharAt(sb.length()-1).toString());
        }
        logger.info("执行方法信息:"+JSONObject.toJSON(operationLog));
        operationLogService.save(operationLog);
    }


    private  String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip+":"+request.getRemotePort();
    }
}
