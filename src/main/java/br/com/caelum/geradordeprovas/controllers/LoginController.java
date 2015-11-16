package br.com.caelum.geradordeprovas.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
public class LoginController {

	private UsuarioDao usuarioDao;

	@Autowired
	public LoginController(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	@RequestMapping("loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@RequestMapping("logout")
	public String logout(HttpSession sessao) {
		sessao.invalidate();
		return "redirect:loginForm";
	}

	@RequestMapping("efetuaLogin")
	public ModelAndView efetuaLogin(@ModelAttribute("usuario") Usuario usuario,
			HttpSession sessao) {

		Usuario user = usuarioDao.validaUsuario(usuario);

		if (user != null) {
			
			sessao.setAttribute("usuario", user);
			
			if (user.isAdmin()) {
				ModelAndView mv = new ModelAndView(new RedirectView(
						"/GeradorDeProvas/admin/index"));
				return mv;
			} else {
				ModelAndView mv = new ModelAndView(new RedirectView(
						"/GeradorDeProvas/provas-liberadas"));
				return mv;
			}
		} else {
			ModelAndView mv = new ModelAndView("loginForm");
			mv.addObject("validacao", false);
			return mv;
		}

	}

}
