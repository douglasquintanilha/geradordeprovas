
package br.com.caelum.geradordeprovas.configuration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.com.caelum.geradordeprovas.dao.AlternativaDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.util.AlternativaArrayConverter;
import br.com.caelum.geradordeprovas.util.AlternativaConverter;
import br.com.caelum.geradordeprovas.util.AlternativaFormatter;
import br.com.caelum.geradordeprovas.util.AlternativaMarcadaArrayConverter;
import br.com.caelum.geradordeprovas.util.AlternativaMarcadaConverter;
import br.com.caelum.geradordeprovas.util.ProvaConverter;
import br.com.caelum.geradordeprovas.util.QuestaoConverter;
import br.com.caelum.geradordeprovas.util.TagConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.caelum.geradordeprovas")
public class Configurator extends WebMvcConfigurerAdapter {
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations(
				"/static/");
	}
	
	

	@Bean
	public FormattingConversionService mvcConversionService(ProvaDao provaDao,AlternativaDao alternativaDao) {
		FormattingConversionService servico = new FormattingConversionService();
		servico.addConverter(new AlternativaConverter());
		servico.addConverter(new AlternativaArrayConverter());
		servico.addConverter(new AlternativaMarcadaConverter(alternativaDao));
		servico.addConverter(new AlternativaMarcadaArrayConverter(alternativaDao));
		servico.addConverter(new TagConverter());
		servico.addConverter(new QuestaoConverter());
		servico.addConverter(new ProvaConverter(provaDao));
		servico.addFormatter(new AlternativaFormatter(alternativaDao));
		return servico;
	}
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("WEB-INF/messages");
		bundle.setDefaultEncoding("ISO-8859-1");
		bundle.setCacheSeconds(1);
		return bundle;
	}
	
	@Bean
	@Scope(value="session")
	@Qualifier("usuarioLogado")
	public Usuario getUsuarioLogado(HttpSession session, UsuarioDao usuarioDao){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Usuario logado = usuarioDao.getUsuarioByLogin(usuario.getLogin());
		return logado;
	}
	

}