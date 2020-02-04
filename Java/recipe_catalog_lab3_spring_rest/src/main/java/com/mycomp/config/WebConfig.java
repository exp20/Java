package com.mycomp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mycomp")
public class WebConfig implements WebMvcConfigurer {
    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    // Static Resource Config
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // xslt resource.
        // /xslt/  - так как вызывается из сервлета на http://localhost:8080/lab3_war/xslt/doctors
        // и в xml ссылка на файл представления: href="resources/"
        registry.addResourceHandler("/xslt/resources/**").addResourceLocations("/WEB-INF/xslt/");
    }
}