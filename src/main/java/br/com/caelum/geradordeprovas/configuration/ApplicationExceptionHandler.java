package br.com.caelum.geradordeprovas.configuration;

import java.io.InputStream;

import javax.persistence.NoResultException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ApplicationExceptionHandler {

	private InputStream devEnviroment = getClass().getClassLoader()
			.getResourceAsStream("devEnviroment.properties");
	
	private String client_error_jsp = "erro";
	private String dev_error_jsp = "devError";
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exception(Exception exception) {
		if (devEnviroment != null)
			return new ModelAndView(dev_error_jsp).addObject("exception", exception);
		else
			return new ModelAndView(client_error_jsp);
	}

	@ExceptionHandler(NoResultException.class)
	public ModelAndView exceptionPersistence(Exception exception) {
		if (devEnviroment != null)
			return new ModelAndView(dev_error_jsp).addObject("exception", exception);
		else
			return new ModelAndView(client_error_jsp);
	}

}
