package com.example.demo.log;


import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
@Aspect
public class LogAspect {
    //切点入口 Controller包下面所有类的所有方法
    private final String pointcut = "execution(* com.example.demo..controller..*.*(..))";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //切点
    @Pointcut(value = pointcut)
    public void log() {
    }



    /**
     * 方法调用之前调用
     *
     * @param joinPoint
     */
    @Before(value = "log()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("执行了切片..........");
    }


    @Around(value = "log()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        String time = "";
        try {
            long startTime = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long endTime = System.currentTimeMillis();
            time = String.valueOf(endTime - startTime);
            MDC.put("ms", time);
        } catch (Throwable e) {
            throw e;
        }
        return result;
    }
}