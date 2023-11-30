package com.jtspringproject.JtSpringProject;
 
import java.util.Properties;
 
import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
    @Value("com.mysql.cj.jdbc.Driver")
    private String DRIVER;
 
    @Value("248248")
    private String PASSWORD;
 
    @Value("jdbc:mysql://localhost:3306/ecommjava")
    private String URL;
 
    @Value("root")
    private String USERNAME;
 
    @Value("org.hibernate.dialect.MySQL5Dialect")
    private String DIALECT;
 
    @Value("true")
    private String SHOW_SQL;
 
    @Value("update")
    private String HBM2DDL_AUTO;
 
    @Value("com.jtspringproject.JtSpringProject")
    private String PACKAGES_TO_SCAN;
 
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
 
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", DIALECT);
        hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
        sessionFactory.setHibernateProperties(hibernateProperties);
 
        return sessionFactory;
    }
 
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }   
}
