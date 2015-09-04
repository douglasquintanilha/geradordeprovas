package com.caelum.geradordeprovas.configuration;

import javax.servlet.Filter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	 
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	return new Class[] { Configurator.class,JpaConfigurator.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
    	return new Class[] {};
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
 
}