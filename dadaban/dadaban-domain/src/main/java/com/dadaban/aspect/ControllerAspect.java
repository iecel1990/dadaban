package com.dadaban.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by jrose on 8/3/14.
 */
@Component
@Aspect
public class ControllerAspect {

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void cutController(){
        System.out.println("controller");
    }


    private static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Around("cutController()")
    public Object appContextHandling(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("controller.aspect...");
        try {
            Object result = joinPoint.proceed();
            return result;
        } catch (Throwable throwable) {
            logger.warn("error:", throwable);
            handleException(throwable, joinPoint);
        }
        return "error/500";
    }

    private void handleException(Throwable ex,ProceedingJoinPoint joinPoint) {

    }

}
