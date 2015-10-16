package br.com.caelum.geradordeprovas.controllers;

import javax.servlet.http.HttpServletRequest;
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
	
	@RequestMapping("loginGitHub")
	public ModelAndView loginGithub(HttpServletRequest request){
		return new ModelAndView("redirect:https://github.com/login/oauth/authorize?client_id=3043231979046f1d8a4b&redirect_uri=http://localhost:8000/GeradorDeProvas/admin/index&scope=repo,user");
	}

	@RequestMapping("loginForm")
	public String loginForm() {
		return "/loginForm";
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
			if (user.isAdmin()) {
				sessao.setAttribute("adminLogado", user);
				ModelAndView mv = new ModelAndView(new RedirectView(
						"/GeradorDeProvas/admin/index"));
				return mv;
			} else {
				sessao.setAttribute("usuarioLogado", user);
				ModelAndView mv = new ModelAndView(new RedirectView(
						"/GeradorDeProvas/"));
				return mv;
			}
		} else {
			ModelAndView mv = new ModelAndView("loginForm");
			mv.addObject("validacao", false);
			return mv;
		}

	}

}
