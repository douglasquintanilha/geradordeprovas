package br.com.caelum.geradordeprovas.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.AlternativaDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@RequestMapping("/avaliacao/")
public class AvaliacaoController {

	private QuestaoDao questaoDao;
	private ProvaDao provaDao;
	private UsuarioDao usuarioDao;
	private AlternativaDao alternativaDao;
	private Usuario usuarioLogado;

	@Autowired
	public AvaliacaoController(QuestaoDao questaoDao, ProvaDao provaDao, UsuarioDao usuarioDao,AlternativaDao alternativaDao,Usuario usuarioLogado) {
		this.questaoDao = questaoDao;
		this.provaDao = provaDao;
		this.usuarioDao = usuarioDao;
		this.alternativaDao = alternativaDao;
		this.usuarioLogado = usuarioLogado;
	}
	
	@RequestMapping("correcao")
	public ModelAndView osdfszdid(@ModelAttribute("avaliacao") Avaliacao avaliacao) {
		avaliacao.setDataRealizada(Calendar.getInstance());
		return new ModelAndView("corrigido");

	}
	
	
}
