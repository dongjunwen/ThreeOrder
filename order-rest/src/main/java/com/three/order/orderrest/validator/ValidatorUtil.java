package com.three.order.orderrest.validator;


import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.except.BusinessException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author luiz
 * @date 2018-07-04 11:09
 */
public class ValidatorUtil{
   /* private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();*/

    /**
     * 校验对象
     * @param object        待校验对象
     * @throws BusinessException  校验不通过，则报BusinessException异常
     */
    public static void validateEntity(Object object,Validator validator){
        if(object==null){
            throw new BusinessException( ResultCode.COMMON_PARAM_NULL.getCode(),"参数不能为空");
        }
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
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
