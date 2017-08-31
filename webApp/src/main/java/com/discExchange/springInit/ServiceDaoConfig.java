package com.discExchange.springInit;


import com.discExchange.AuthController;
import com.discExchange.DiscController;
import com.discExchange.dao.*;
import com.discExchange.service.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.support.ResourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
@PropertySource("classpath:/jdbc.properties")
public class ServiceDaoConfig {

    @Bean(name = "authController")
    public AuthController getAuthController(@Qualifier("userService") UserService userService) {
        AuthController authController = new AuthController();
        authController.setUserService(userService);
        return authController;
    }

    @Bean(name = "discController")
    public DiscController getDiscController(
            @Qualifier("takenDiscService") TakenDiscService takenDiscService,
            @Qualifier("discService") DiscService discService,
            @Qualifier("userService") UserService userService) {
        DiscController discController = new DiscController();
        discController.setTakenDiscService(takenDiscService);
        discController.setDiscService(discService);
        discController.setUserService(userService);
        return discController;
    }

    @Bean(name = "takenDiscService")
    public TakenDiscService getTakenDiscService(@Qualifier("takenDiscDAO")TakenDiscDAO takenDiscDAO){
        TakenDiscServiceImpl result = new TakenDiscServiceImpl();
        result.setTakenDiscDAO(takenDiscDAO);
        return result;
    }

    @Bean(name = "takenDiscDAO")
    public TakenDiscDAO getTakenDiscDAO(@Qualifier("hibernate4AnnotatedSessionFactory") SessionFactory sessionFactory){
        TakenDiscDAOImpl result = new TakenDiscDAOImpl();
        result.setSessionFactory(sessionFactory);
        return result;
    }

    @Bean(name = "discService")
    public DiscService getDiscService(@Qualifier("discDAO") DiscDAO discDao)
    {
        DiscServiceImpl userService = new DiscServiceImpl();
        userService.setDiscDAO(discDao);
        return userService;
    }

    @Bean(name = "discDAO")
    public DiscDAO getDiscDao(@Qualifier("hibernate4AnnotatedSessionFactory") SessionFactory sessionFactory ) {
        DiscDAOImpl discDAO = new DiscDAOImpl();
        discDAO.setSessionFactory(sessionFactory);
        return discDAO;
    }


    @Bean(name = "userService")
    public UserService getUserService(@Qualifier("userDAO") UserDAO userDAO)
    {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDAO(userDAO);
        return userService;
    }

    @Bean(name = "userDAO")
    public UserDAO getUserDao(@Qualifier("hibernate4AnnotatedSessionFactory") SessionFactory sessionFactory ) {
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setSessionFactory(sessionFactory);
        return userDAO;
    }

    @Bean(name = "hibernate4AnnotatedSessionFactory")
    public FactoryBean<SessionFactory> getSessionFactory(
            @Qualifier("dataSource") DataSource dataSource,
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.show_sql}") String showSql
            ) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);

        localSessionFactoryBean.setPackagesToScan("com.discExchange.model");
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", dialect);
        hibernateProperties.setProperty("hibernate.show_sql", showSql);
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);

        return localSessionFactoryBean;
    }

    //@Profile("dev")
    @Bean(name="dataSource"/*, destroyMethod = "close"*/ )
    public DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
                .addScript("hsqldb/create-db.sql")
                .addScript("hsqldb/insert-data.sql")
                .build();
        return db;
    }

    @Profile("production")
    @SuppressWarnings({ "ContextJavaBeanUnresolvedMethodsInspection"})
    @Bean(name="dataSource", destroyMethod = "close" )
    public DataSource dataSourceMsSql(
            @Value("${dataSource.url}") String url,
            @Value("${dataSource.driverClassName}") String driverClassName,
            @Value("${dataSource.username}") String username,
            @Value("${dataSource.password}") String password
    ) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setPassword(url);
        dataSource.setPassword(driverClassName);
        dataSource.setPassword(username);
        dataSource.setPassword(password);

        return dataSource;
    }



    @Bean(name = "transactionManager")
    public ResourceTransactionManager getTransactionManager(
            @Qualifier("hibernate4AnnotatedSessionFactory") SessionFactory sessionFactory) {
        HibernateTransactionManager result = new HibernateTransactionManager();
        result.setSessionFactory(sessionFactory);
        return result;
    }
}
