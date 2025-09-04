package com.phm.aspect;


import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
public class LoginAspect {

    @Around("execution(* com.gecp.helper.DBService.*(..))")
    public Object DBMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[LOG] Method start: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("[LOG] Method end: " + joinPoint.getSignature().getName());
        return result;
    }

}