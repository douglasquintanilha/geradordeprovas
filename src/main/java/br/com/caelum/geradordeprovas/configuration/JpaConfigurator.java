package br.com.caelum.geradordeprovas.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaConfigurator {
	
	@Autowired
	private Constantes constantes;
	
	@Bean
	@Profile("dev")
	public DataSource dataSource() {
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setUrl(constantes.getProperty("BdUrlDev"));
		dm.setUsername(constantes.getProperty("BdUsernameDev"));
		dm.setPassword(constantes.getProperty("BdUrlPasswordDev"));
		dm.setDriverClassName(constantes.getProperty("BdDriverDev"));

		return dm;
	}
	
	@Bean
	@Profile("producao")
	public DataSource dataSourceProducao() {
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setUrl("jdbc:mysql://" + System.getenv().get("OPENSHIFT_MYSQL_DB_HOST")+":" + System.getenv().get("OPENSHIFT_MYSQL_DB_PORT") + "caelum_provas");
		dm.setUsername(constantes.getProperty("BdUsernameProducao"));
		dm.setPassword(constantes.getProperty("BdUrlPasswordProducao"));
		dm.setDriverClassName(constantes.getProperty("BdDriverProducao"));

		return dm;
	}
	
	@Bean
	@Profile("test")
	public DataSource dataSourceTest() {
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setUrl(constantes.getProperty("BdUrlTest"));
		dm.setUsername(constantes.getProperty("BdUsernameTest"));
		dm.setPassword(constantes.getProperty("BdUrlPasswordTest"));
		dm.setDriverClassName(constantes.getProperty("BdDriverTest"));

		return dm;
	}


	@Bean
	public LocalContainerEntityManagerFactoryBean emf(DataSource ds) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(ds);

		emf.setPackagesToScan("br.com.caelum.geradordeprovas.models");

		HibernateJpaVendorAdapter jpa = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(jpa);

		Properties property = new Properties();
		property.setProperty("hibernate.hbm2ddl.auto", "update");
		property.setProperty("hibernate.show_sql", "true");
		property.setProperty("hibernate.dialect",
				"org.hibernate.dialect.MySQL5InnoDBDialect");
		emf.setJpaProperties(property);

		return emf;

	}

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

}
