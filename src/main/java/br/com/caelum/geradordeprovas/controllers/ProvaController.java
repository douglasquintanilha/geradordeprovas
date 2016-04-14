package br.com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.LiberacaoForm;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@RequestMapping("/admin/prova")
public class ProvaController {

	private QuestaoDao questaoDao;
	private ProvaDao provaDao;
	private UsuarioDao usuarioDao;

	@Autowired
	public ProvaController(QuestaoDao questaoDao, ProvaDao provaDao,
			UsuarioDao usuarioDao) {
		this.questaoDao = questaoDao;
		this.provaDao = provaDao;
		this.usuarioDao = usuarioDao;
	}

	@RequestMapping("/monta")
	public ModelAndView montarProvaView() {
		ModelAndView mv = new ModelAndView("admin/montar-prova");
		List<Questao> questoes = questaoDao.list();
		mv.addObject("questoes", questoes);
		return mv;
	}

	@Transactional
	@RequestMapping("/salvaProva")
	public ModelAndView salvaProva(@Valid @ModelAttribute("prova") Prova prova,
			BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("admin/montar-prova",
					result.getModel());
			List<Questao> questoes = questaoDao.list();
			mv.addObject("questoes", questoes);
			return mv;
		}

		prova.setDataCriacao(Calendar.getInstance());
		
		provaDao.save(prova);

		ModelAndView mv = new ModelAndView("admin/prova-adicionada");

		return mv;
	}

	@RequestMapping("/libera")
	public String liberaProva(Model model) {

		List<Usuario> usuarios = new ArrayList<>(usuarioDao.list());
		List<Prova> provas = new ArrayList<>(provaDao.list());

		model.addAttribute("usuarios", usuarios);
		model.addAttribute("provas", provas);

		return "admin/libera-prova";

	}

	@Transactional
	@RequestMapping("/salvaLiberacao")
	public ModelAndView salvaLiberacao(@ModelAttribute("liberacaoForm") @Valid LiberacaoForm liberacaoForm, BindingResult result,RedirectAttributes attr) {
		if(result.hasErrors()){
			// Wesley
			attr.addFlashAttribute("org.springframework.validation.BindingResult.liberacaoForm", result);
			ModelAndView mv = new ModelAndView("redirect:libera");
			return mv;
		}
		List<Prova> provas = provaDao.getProvasPorIds(liberacaoForm.getProvas());

		for (Long usuarioId : liberacaoForm.getUsuarios()) {
			usuarioDao.salvaProvasLiberadas(usuarioId, provas);
		}
		
		ModelAndView mv = new ModelAndView("admin/provas-liberadas");
		return mv;
	}
	
	@RequestMapping("/listar")
	public ModelAndView listar(){

		List<Prova> provas = provaDao.list();		
		ModelAndView mv = new ModelAndView("admin/listar-provas");

		mv.addObject("provas",provas);
		return mv;
	}
	
	@RequestMapping(value="/editar/{id}",method=RequestMethod.GET)
	public ModelAndView editarForm(@PathVariable long id){
		Prova prova = provaDao.getProva(id);
		
		List<Questao> questoesDaProva = prova.getQuestoes();
		List<Long> ids = new ArrayList<Long>();
		
		for (Questao questao : questoesDaProva) {
			ids.add(questao.getId());
		}
		
		List<Questao> questoesQueNaoFazParteDaProva = questaoDao.getQuestaoExceto(ids);
		
		ModelAndView mv = new ModelAndView("admin/editar-prova");
		
		mv.addObject("questoesDaProva", questoesDaProva);
		mv.addObject("questoes",questoesQueNaoFazParteDaProva);
		mv.addObject("prova", prova);
		return mv;	
	}
	
	@Transactional
	@RequestMapping(value="/editar/{id}",method=RequestMethod.POST)
	public ModelAndView editarQuestao(@Valid @ModelAttribute("prova") Prova prova,@PathVariable long id, BindingResult result,RedirectAttributes flash){
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("admin/editar-prova/"+id,
					result.getModel());
			List<Questao> questoesDaProva = prova.getQuestoes();
			List<Long> ids = new ArrayList<Long>();
			
			for (Questao questao : questoesDaProva) {
				ids.add(questao.getId());
			}
			
			List<Questao> questoesQueNaoFazParteDaProva = questaoDao.getQuestaoExceto(ids);
			
			mv.addObject("questoesDaProva", questoesDaProva);
			mv.addObject("questoes",questoesQueNaoFazParteDaProva);
			mv.addObject("prova", prova);
			return mv;
		}
		
		
		ModelAndView mv = new ModelAndView("redirect:../listar");
		
		provaDao.update(prova);
		flash.addFlashAttribute("prova",prova);
		
		return mv;
	}

}
