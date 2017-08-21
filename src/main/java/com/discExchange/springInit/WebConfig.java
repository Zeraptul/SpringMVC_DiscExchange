package com.discExchange.springInit;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-java
//@ContextConfiguration
@Configuration
@EnableWebMvc
//https://spring.io/blog/2011/06/10/spring-3-1-m2-configuration-enhancements/
@EnableTransactionManagement
@Import(ServiceDaoConfig.class)
//Еще можно так https://github.com/spring-projects/greenhouse/commit/213d2c742d472e602defcde801dd118e098d73c6
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        //registry.addViewController("").
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        registry.viewResolver(resolver);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new AuthUserAccessInterceptor());
       /* registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");*/
    }


}

