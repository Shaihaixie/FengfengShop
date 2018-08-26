package com.neuedu.aspect;
import com.neuedu.dao.LogDao;
import com.neuedu.entity.LogBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class LogAspect {
    @Autowired
    LogDao logDao;
    //包及其子包
    @Pointcut("execution(* com.neuedu.service.impl.*.*(..))")
    public  void   mm(){}
    @Around(value = "mm()")
    public  Object  around(ProceedingJoinPoint proceedingJoinPoint){

        System.out.println("====环绕通知====");
        String className= proceedingJoinPoint.getTarget().getClass().getName();
        String methodName=proceedingJoinPoint.getSignature().getName();
        Object o=null;
        try {
            o=proceedingJoinPoint.proceed();
            LogBean logBean=new LogBean(1,className+methodName);
            logDao.add(logBean);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return o;
    }

}







