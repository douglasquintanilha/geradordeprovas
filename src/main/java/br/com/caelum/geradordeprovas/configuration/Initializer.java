package br.com.caelum.geradordeprovas.configuration;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	 
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	return new Class[] { Configurator.class,JpaConfigurator.class };
    }
  
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(new TempoSessao());
        servletContext.setInitParameter("spring.profiles.active", "dev");
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
    	return new Filter[]{new OpenEntityManagerInViewFilter()};
    }
    
 
}