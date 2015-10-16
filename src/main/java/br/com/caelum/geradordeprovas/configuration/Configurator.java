package br.com.caelum.geradordeprovas.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.com.caelum.geradordeprovas.util.AlternativaConverter;
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
	public FormattingConversionService mvcConversionService() {
		FormattingConversionService servico = new FormattingConversionService();
		servico.addConverter(new AlternativaConverter());
		servico.addConverter(new TagConverter());
		servico.addConverter(new QuestaoConverter());
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
	

}