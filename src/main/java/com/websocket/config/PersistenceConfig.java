package com.websocket.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("com.websocket")
@EnableTransactionManagement
public class PersistenceConfig {

	@Bean
	   public LocalSessionFactoryBean sessionFactory() {
	      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	      sessionFactory.setDataSource(dataSource());
	      sessionFactory.setPackagesToScan(new String[] { "com.websocket.model" });
	      sessionFactory.setHibernateProperties(hibernateProperties());
	      return sessionFactory;
	   }
	 
	   @Bean
	   public DataSource dataSource() {
	      BasicDataSource dataSource = new BasicDataSource();
	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	      dataSource.setUrl("jdbc:mysql://localhost:3306/websocket");
	      dataSource.setUsername("root");
	      dataSource.setPassword("");
	 
	      return dataSource;
	   }
	   
	   @Bean
	   @Autowired
	   public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
	      HibernateTransactionManager txManager = new HibernateTransactionManager();
	      txManager.setSessionFactory(sessionFactory);
	      return txManager;
	   }
	   
	   @SuppressWarnings("serial")
	Properties hibernateProperties() {
		      return new Properties() {
		         {
		            setProperty("hibernate.hbm2ddl.auto", "update");
		            setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		            setProperty("hibernate.globally_quoted_identifiers", "true");
		            setProperty("hibernate.show_sql", "true");	
		         }
		      };
		   }
}
