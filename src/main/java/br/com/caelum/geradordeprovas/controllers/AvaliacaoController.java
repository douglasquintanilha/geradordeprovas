package br.com.caelum.geradordeprovas.controllers;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.dao.EstatisticaQuestaoDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.EstatisticaQuestao;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@RequestMapping("/avaliacao/")
@Scope("request")
public class AvaliacaoController {


	private Usuario usuarioLogado;
	private AvaliacaoDao avaliacaoDao;
	private ProvaDao provaDao;
	private EstatisticaQuestaoDao estatisticaQuestaoDao;
	
	@Autowired
	public AvaliacaoController(@Qualifier("usuarioLogado") Usuario usuarioLogado,ProvaDao provaDao,AvaliacaoDao avaliacaoDao, EstatisticaQuestaoDao estatisticaQuestaoDao) {
		this.usuarioLogado = usuarioLogado;
		this.avaliacaoDao = avaliacaoDao;
		this.estatisticaQuestaoDao = estatisticaQuestaoDao;
	}
	
	
	@Transactional
	@RequestMapping(value = "correcao", method={RequestMethod.POST})
	public ModelAndView corrigePost(@ModelAttribute("avaliacao") Avaliacao avaliacao, HttpSession session) {
		if(session.getAttribute("avaliacao") != null){
			return new ModelAndView("redirect:refaz");
		}else{
			avaliacao.setHorarioInicio((Calendar)session.getAttribute("horarioInicio"));
			avaliacao.setHorarioFim(Calendar.getInstance());	
			avaliacao.setUsuario(usuarioLogado);
			avaliacao.validaDuracao();
			avaliacao.corrige();
			avaliacaoDao.save(avaliacao);
			session.setAttribute("avaliacao", avaliacao);
			return new ModelAndView("redirect:correcao");
		}
	}
	
	@Transactional 
	@RequestMapping(value = "correcao", method={RequestMethod.GET})
	public ModelAndView corrigeGet(@ModelAttribute("avaliacao") Avaliacao avaliacao, HttpSession session) {
		avaliacao = (Avaliacao) session.getAttribute("avaliacao");
		avaliacao = avaliacaoDao.atualiza(avaliacao);
		return new ModelAndView("corrigido").addObject("avaliacao", avaliacao);
	}
	

	
	
	@RequestMapping("refaz")
	public String refazerAvaliacao(){
		return "refazAvaliacao";
	}
}