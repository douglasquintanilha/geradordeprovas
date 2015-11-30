package br.com.caelum.geradordeprovas.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@RequestMapping("/")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(session.getAttribute("avaliacao") != null ){
			session.removeAttribute("avaliacao");
		}		
		
		if (usuario.isAdmin()) {
			return "redirect:admin/index";
		} else
			return "redirect:/liberadas";
	}

	@RequestMapping("index")
	public String index(){
		return "index";
	}
}