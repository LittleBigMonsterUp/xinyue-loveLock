package com.feng.ycnweapp;

import com.feng.ycnweapp.security.JwtAuthenticationTokenFilter;
import com.feng.ycnweapp.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EntityScan("com.feng.framework.ycnweapp")
@ComponentScan(basePackages = {"com.feng.api"})
@ComponentScan(basePackages = {"com.feng.framework"})
public class YcnweappApplication {

    public static void main(String[] args) {

        SpringApplication.run(YcnweappApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
    @Bean
    public FilterRegistrationBean<JwtAuthenticationTokenFilter> registration(JwtAuthenticationTokenFilter filter) {
        FilterRegistrationBean<JwtAuthenticationTokenFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

}
