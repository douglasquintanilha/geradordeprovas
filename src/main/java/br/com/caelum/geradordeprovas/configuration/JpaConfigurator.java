package br.com.caelum.geradordeprovas.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;
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

import br.com.caelum.geradordeprovas.oauth.GithubApi;

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
	public OAuthService prepareOAuthServiceProducao() throws IOException {
		return new ServiceBuilder()
				.provider(GithubApi.class)
				.apiKey(constantes.getProperty("apiKeyProducao"))
				.apiSecret(constantes.getProperty("apiSecretProducao"))
				.callback(constantes.getProperty("apiUrlCallbackProducao"))
				.build();
	}
	
	@Bean
	@Profile("dev")
	public OAuthService prepareOAuthServiceDev() throws IOException {
		return new ServiceBuilder()
				.provider(GithubApi.class)
				.apiKey(constantes.getProperty("apiKeyDev"))
				.apiSecret(constantes.getProperty("apiSecretDev"))
				.callback(constantes.getProperty("apiUrlCallbackDev"))
				.build();
	}
	
	
	
	@Bean
	@Profile("producao")
	public DataSource dataSourceProducao() {
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setUrl("jdbc:mysql://" + System.getenv().get("OPENSHIFT_MYSQL_DB_HOST")+":" + System.getenv().get("OPENSHIFT_MYSQL_DB_PORT") + "/" + "caelum_provas");
		dm.setUsername(constantes.getProperty("BdUsernameProducao"));
		dm.setPassword(constantes.getProperty("BdPasswordProducao"));
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
