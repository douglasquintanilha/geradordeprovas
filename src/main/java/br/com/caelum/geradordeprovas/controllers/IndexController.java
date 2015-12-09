package br.com.caelum.geradordeprovas.controllers;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@RequestMapping("/")
public class IndexController {

	private static final Logger LOG = LogManager.getLogger(IndexController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (session.getAttribute("avaliacao") != null) {
			session.removeAttribute("avaliacao");
		}

		if (usuario.isAdmin()) {
			return "redirect:admin/index";
		} else
			return "redirect:/liberadas";
	}

	@RequestMapping("index")
	public String index() {
		return "index";
	}
}