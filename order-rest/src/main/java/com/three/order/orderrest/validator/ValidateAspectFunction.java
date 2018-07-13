package com.three.order.orderrest.validator;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.lang.reflect.Method;

/**
 * Created by on 2018/7/13 0013.
 */
@Aspect
@Component
@Slf4j
public class ValidateAspectFunction {

    @Autowired
    private Validator validator;

    //匹配com.zkn.learnspringboot.web.controller包及其子包下的所有类的所有方法
    @Pointcut("execution(* com.three.order.orderrest.controller.*.*(..))")
    public void aspectPointcut(){

    }

    @Around("aspectPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        String methodName = "";
        try{
            Signature sig = pjp.getSignature();
            MethodSignature msig = (MethodSignature) sig;
            Object target = pjp.getTarget();
            Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            methodName = currentMethod.getName();
        } catch (Exception e){
            log.debug("无法获取方法名称" ,e);
        }
        Object[] args = pjp.getArgs();
        for (Object arg : args){
            if(arg != null){
                ValidatorUtil.validateEntity(arg,validator);
            }
        }
        Object object = pjp.proceed();
        log.debug("→→→→→" + methodName + ">>>>Return to the result :" + JSONObject.toJSONString(object));
        return object;
    }
}
