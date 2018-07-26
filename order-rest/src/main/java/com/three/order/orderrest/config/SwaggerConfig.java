package com.three.order.orderrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger-ui的配置
 * api页面 /swagger-ui.html 或者/swagger/index.html ()
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.three.order.orderrest.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("三人行订单系统")
                .termsOfServiceUrl("XXXX")
                .description("三人行订单系统")
                .contact(new Contact("luiz","XXX","donglu90@163.com"))
                .version("1.0.0")
                .build();
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1
        corsConfiguration.addAllowedHeader("*"); // 2
        corsConfiguration.addAllowedMethod("*"); // 3
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }


    @Bean
    public HandlerInterceptor getInterceptor(){
        return  new LoginInterceptor();
    }

    protected void addInterceptors(InterceptorRegistry registry) {
        //需要登录的接口需要在这里配置
        registry.addInterceptor(getInterceptor()).addPathPatterns("/**").excludePathPatterns("/api/user/login")
                .excludePathPatterns("/api/pay/notifyResult")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                .excludePathPatterns("/api/pay/**")
                .excludePathPatterns("/show/**")
                .excludePathPatterns("/api/search/**")
                .excludePathPatterns("/api/cart/**");
        super.addInterceptors(registry);
    }

}
