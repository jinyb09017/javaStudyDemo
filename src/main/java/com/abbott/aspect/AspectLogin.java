package com.abbott.aspect;

import com.abbott.annotation.CheckLogin;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;


@Aspect
public class AspectLogin {
    private static final String TAG = "CheckLogin";

    /**
     * 找到处理的切点
     * * *(..)  可以处理CheckLogin这个类所有的方法
     */
    @Pointcut("execution(@com.abbott.annotation.CheckLogin  * *(..))")
    public void executionCheckLogin() {
    }

    /**
     * 处理切面
     *
     * @param joinPoint
     * @return
     */
    @Around("executionCheckLogin()")
    public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckLogin checkLogin = signature.getMethod().getAnnotation(CheckLogin.class);

        if (checkLogin != null) {

            String page = checkLogin.page();
            if (RightManager.getRight().hasRight(page)) {
                System.out.println("has right 1");
                return joinPoint.proceed();
            } else {
                System.out.println("not right 1");
                return null;
            }
        }else{
            System.out.println("CheckLogin not register 1");
        }
        return joinPoint.proceed();
    }
}
