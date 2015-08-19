package com.caelum.geradordeprovas.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.Hibernate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class JpaConfigurator {
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setUrl("jdbc:mysql://localhost/caelum_provas");
		dm.setUsername("root");
		dm.setPassword("");
		dm.setDriverClassName("com.mysql.jdbc.Driver");
		
		return dm;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean emf(DataSource ds){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(ds);
		
		emf.setPackagesToScan("com.caelum.geradordeprovas.models");

		HibernateJpaVendorAdapter jpa = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(jpa);
		
		Properties property = new Properties();
		property.setProperty("hibernate.hbm2ddl.auto", "update");
		property.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		emf.setJpaProperties(property);
		
		return emf;
		
	}
	
}
