package br.com.caelum.geradordeprovas.controllers;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.dao.FeedbackDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.Feedback;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.util.Criptografia;

@Controller
@Scope("request")
public class UsuarioController {

	private ProvaDao provaDao;
	private Usuario usuarioLogado;
	private AvaliacaoDao avaliacaoDao;
	private FeedbackDao feedbackDao;
	private UsuarioDao usuarioDao;
	private Criptografia criptografia;


	@Autowired
	public UsuarioController(@Qualifier("usuarioLogado") Usuario usuarioLogado,
			ProvaDao provaDao, AvaliacaoDao avaliacaoDao, FeedbackDao feedbackDao,
			UsuarioDao usuarioDao,Criptografia criptografia) {
		this.provaDao = provaDao;
		this.feedbackDao = feedbackDao;
		this.usuarioLogado = usuarioLogado;
		this.avaliacaoDao = avaliacaoDao;
		this.usuarioDao = usuarioDao;
		this.criptografia = criptografia;
	}

	@RequestMapping("/avaliacao/{provaUuid}")
	public ModelAndView escolheProva(@RequestParam("provaId") Prova prova,
			HttpSession session) {

		List<Avaliacao> avaliacoes = avaliacaoDao.getAvaliacoesPor(
				usuarioLogado, prova);
		if (avaliacoes.isEmpty()) {
			session.setAttribute("horarioInicio", Calendar.getInstance());
			return new ModelAndView("realiza-prova").addObject("prova",
					provaDao.getProva(prova.getId()).embaralha());

		}

		ModelAndView mv = new ModelAndView("lista-provas-realizadas")
				.addObject("avaliacoes", avaliacoes);
		mv.addObject("idProva", prova.getId());

		return mv;
	}

	@RequestMapping("avaliacao/realiza")
	public ModelAndView realizaProva(@RequestParam("provaId") Prova prova,
			HttpSession session) {
		session.setAttribute("horarioInicio", Calendar.getInstance());
		Prova provaEmbaralhada = provaDao.getProva(prova.getId()).embaralha();
		return new ModelAndView("realiza-prova").addObject("prova",
				provaEmbaralhada);
	}

	@RequestMapping("avaliacao/realizada/{avaliacaoUuid}")
	public ModelAndView provaRealizada(@RequestParam("avaliacaoId") Avaliacao avaliacao) {
		return new ModelAndView("corrigido").addObject("avaliacao",
				avaliacao);
	}

	@RequestMapping("/liberadas")
	public ModelAndView provasLiberadas() {

		return new ModelAndView("provas-liberadas").addObject("provas",
				provaDao.getProvasLiberadasByUsuario(usuarioLogado));
	}

	@Transactional
	@RequestMapping("feedback")
	public ModelAndView feedback(
			@Valid @ModelAttribute("feedback") Feedback feedback,
			BindingResult result) {
		if(result.hasErrors()){
			
		}
		feedback.setDataCriacao(Calendar.getInstance());
		feedback.setUsuario(usuarioLogado);
		feedbackDao.save(feedback);
		return new ModelAndView("feedback");
	}
	
	@RequestMapping("usuario/listar")
	public ModelAndView listar() {

		List<Usuario> usuarios = usuarioDao.list();
		ModelAndView mv = new ModelAndView("admin/listar-usuarios");

		mv.addObject("usuarios", usuarios);
		return mv;
	}
	
	@RequestMapping(value = "usuario/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarForm(@PathVariable long id) {
		Usuario usuario = usuarioDao.find(id);

		ModelAndView mv = new ModelAndView("admin/editar-usuario");

		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@RequestMapping(value = "usuario/editar/{id}", method = RequestMethod.POST)
	public ModelAndView editarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, 
			@PathVariable long id,BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("admin/editar-usuario/" + id, result.getModel());
			return mv;
		}	
		ModelAndView mv = new ModelAndView("redirect:../listar");
		
		String senhaCriptografada = criptografia.criptografaSenha(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		usuarioDao.atualiza(usuario);
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	
	
}