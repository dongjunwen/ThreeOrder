package com.three.order.orderrest;

import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@SpringBootApplication
@ComponentScan({"com.three.order","com.three.order.orderrest.validator"})
public class OrderRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderRestApplication.class, args);
	}

	@Bean
	public Validator getValidatorFactory(){
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory();
		return validatorFactory.getValidator();
	}


}
