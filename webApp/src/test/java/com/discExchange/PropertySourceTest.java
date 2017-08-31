package com.discExchange;


import com.discExchange.springInit.MyWebAppInitializer;
import com.discExchange.springInit.ServiceDaoConfig;
import com.discExchange.springInit.WebConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletConfig;

//@ContextConfiguration("classpath:spring/servlet-context-test_2.xml")
//@ContextConfiguration(classes = PropertySourceTest.AppConfig.class)
//@EnableWebMvc
//@WebAppConfiguration
/*@ContextConfiguration(
        classes = {WebConfig.class, PropertySourceTest.AppConfig.class},
        loader = AnnotationConfigContextLoader.class
)*/
@WebAppConfiguration
//@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes = {PropertySourceTest.AppConfig.class, WebConfig.class})
@ContextHierarchy(
        {
        @ContextConfiguration(loader=AnnotationConfigContextLoader.class),
         @ContextConfiguration(classes = {WebConfig.class,PropertySourceTest.AppConfig.class})/*,
         @ContextConfiguration(classes = WebConfig.class)*/
}        )
@RunWith(SpringJUnit4ClassRunner.class)
public class PropertySourceTest {

    @Configuration
    @PropertySource("classpath:/app2.properties")
    //@Import(WebConfig.class)
    public static class AppConfig{

        /*//http://www.baeldung.com/properties-with-spring
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }*/
    }

    @Value("${myProperty}")
    String myValue;

    @Autowired
    @Qualifier("authController")
    AuthController authController;

    @Autowired
    Environment environment;

    @Test
    public void run(){
        Assert.assertNotNull(authController);
    }

    @Test
    public void getValueTest() {

        String propValue = environment.getProperty("myProperty");
        Assert.assertEquals("success", myValue);
        Assert.assertEquals("success", propValue);

    }
}
