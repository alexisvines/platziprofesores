package com.alexisvines.profesoresplatzi.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Alexisvines
 * 
 *
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan("com.alexisvines.profesoresplatzi.model");
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		return sessionFactoryBean;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/platziprofesores");
		dataSource.setUsername("platziprofesores");
		dataSource.setPassword("platziprofesores");

		return dataSource;
	}

	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate_dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("show_sql", true);
		return properties;
	}

	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());

		return hibernateTransactionManager;

	}

}