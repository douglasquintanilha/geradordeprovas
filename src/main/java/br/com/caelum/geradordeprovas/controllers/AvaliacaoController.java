package br.com.caelum.geradordeprovas.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@RequestMapping("/avaliacao/")
@Scope("request")
public class AvaliacaoController {


	private Usuario usuarioLogado;
	private AvaliacaoDao avaliacaoDao;

	@Autowired
	public AvaliacaoController(@Qualifier("usuarioLogado") Usuario usuarioLogado,AvaliacaoDao avaliacaoDao) {
		this.usuarioLogado = usuarioLogado;
		this.avaliacaoDao = avaliacaoDao;
	}
	
	@Transactional
	@RequestMapping("correcao")
	public ModelAndView corrige(@ModelAttribute("avaliacao") Avaliacao avaliacao) {
		avaliacao.setDataRealizada(Calendar.getInstance());
		avaliacao.corrige();
		avaliacao.setUsuario(usuarioLogado);
		avaliacaoDao.save(avaliacao);
		return new ModelAndView("corrigido");

	}
	
	
}
