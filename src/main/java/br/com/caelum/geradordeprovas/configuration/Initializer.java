package br.com.caelum.geradordeprovas.configuration;

import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	private InputStream devEnviroment = getClass().getClassLoader()
			.getResourceAsStream("devEnviroment.properties");

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { Configurator.class, JpaConfigurator.class };
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		super.onStartup(servletContext);
		if (devEnviroment != null)
			servletContext.setInitParameter("spring.profiles.active", "dev");
		else
			servletContext.setInitParameter("spring.profiles.active", "producao");
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new OpenEntityManagerInViewFilter() };
	}

}