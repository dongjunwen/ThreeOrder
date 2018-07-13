package com.three.order.orderrest.validator;

import com.alibaba.fastjson.JSON;
import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.except.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by on 2018/7/13 0013.
 */
@Aspect
@Component
public class ValidateAspectFunction {

    @Autowired
    private Validator validator;

    private static Logger log= LoggerFactory.getLogger(ValidateAspectFunction.class);
    //匹配com.zkn.learnspringboot.web.controller包及其子包下的所有类的所有方法
    @Pointcut("execution(* com.three.order.orderrest.controller.*.*(..))")
    public void aspectJMethod(){

    }

    @Around("aspectJMethod()")
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
                Set<ConstraintViolation<Object>> constraintViolations = validator.validate(arg);
                if (!constraintViolations.isEmpty()) {
                    StringBuilder msg = new StringBuilder();
                    for(ConstraintViolation<Object> constraint:  constraintViolations){
                        if(StringUtils.isNotBlank(msg.toString())){
                            msg.append("\n");
                        }
                        msg.append(constraint.getMessage());
                    }
                    throw new BusinessException( ResultCode.COMMON_PARAM_INVALID.getCode(),msg.toString());
                }
            }
        }
        Object object = pjp.proceed();
        log.debug("→→→→→" + methodName + ">>>>Return to the result :" + JSON.toJSONString(object));
        return object;
    }
}
