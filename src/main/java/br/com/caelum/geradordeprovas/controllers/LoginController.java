package br.com.caelum.geradordeprovas.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.util.Criptografia;

@Controller
public class LoginController {

	private UsuarioDao usuarioDao;
	private Criptografia criptografia;

	@Autowired
	public LoginController(UsuarioDao usuarioDao, Criptografia criptografia) {
		this.usuarioDao = usuarioDao;
		this.criptografia = criptografia;
	}

	@RequestMapping("login")
	public String loginForm() {
		return "loginForm";
	}
	
	@RequestMapping("cadastro")
	public String cadastroView(){
		return "cadastro";
	}
	
	@Transactional
	@RequestMapping("efetuaCadastro")
	public String efetuaCadastro(@ModelAttribute("Usuario") Usuario usuario, RedirectAttributes flash){
		if(usuarioDao.loginExistente(usuario.getLogin())){
			flash.addFlashAttribute("usuarioExistente", true);
			return "redirect:cadastro";
		}
		usuario.setSenha(criptografia.criptografaSenha(usuario.getSenha()));
		usuarioDao.save(usuario);
		flash.addFlashAttribute("usuarioCriado", true);
		return "redirect:login";
	}

	@RequestMapping("logout")
	public String logout(HttpSession sessao) {
		sessao.invalidate();
		return "redirect:login";
	}

	@RequestMapping("efetuaLogin")
	public ModelAndView efetuaLogin(@ModelAttribute("usuario") Usuario usuario,
			HttpSession sessao) {

		Usuario user = usuarioDao.validaUsuario(usuario);
		if (user != null) {

			sessao.setAttribute("usuario", user);

			if (user.isAdmin()) {
				ModelAndView mv = new ModelAndView(new RedirectView(
						"admin/index"));
				return mv;
			} else {
				ModelAndView mv = new ModelAndView(
						new RedirectView("liberadas"));
				return mv;
			}
		} else {
			ModelAndView mv = new ModelAndView("loginForm");
			mv.addObject("validacao", false);
			return mv;
		}

	}

}
