package br.com.caelum.geradordeprovas.databuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.caelum.geradordeprovas.models.Alternativa;
import br.com.caelum.geradordeprovas.models.EstatisticaQuestao;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Tag;

public class QuestaoBuilder {

	private Long id;

	private String titulo;

	private Set<Tag> tags = new HashSet<>();

	private EstatisticaQuestao estatistica = new EstatisticaQuestao();

	private List<Alternativa> alternativa;

	private String alternativaCorreta;

	public Questao geraQuestao() {
		Questao questao = new Questao();
		questao.setId(id);
		questao.setTitulo(titulo);
		questao.setTags(tags);
		questao.setEstatistica(estatistica);
		questao.setAlternativa(alternativa);
		questao.setAlternativaCorreta(alternativaCorreta);
		return questao;
	}

	public Questao geraQuestaoDefault(){	
		Set<Tag> tags = new HashSet<Tag>();
		tags.add(new Tag(1l,"Java"));
		tags.add(new Tag(2l,"C"));
		
		Alternativa alternativa1 = new Alternativa(1l, "Uma alternativa", true);
		Alternativa alternativa2 = new Alternativa(2l, "Uma alternativa", false);
		Alternativa alternativa3 = new Alternativa(3l, "Uma alternativa", false);
		Alternativa alternativa4 = new Alternativa(4l, "Uma alternativa", false);
		Alternativa alternativa5 = new Alternativa(5l, "Uma alternativa", false);
		
		List<Alternativa> alternativas = Arrays.asList(alternativa1,alternativa2,alternativa3
												,alternativa4,alternativa5);
		
		QuestaoBuilder builder =  new QuestaoBuilder();
		builder.comId(1l).comTitulo("Questao Teste").comTags(tags).comEstatiscaQuestao(null)
						.comAlternativas(alternativas).comAlternativaCorreta("1");
		return builder.geraQuestao();
	}
	
	public List<Questao> geraListaDeQuestoes(){
		QuestaoBuilder builder =  new QuestaoBuilder();
		Questao q1 = builder.geraQuestaoDefault();
		Questao q2 = builder.geraQuestaoDefault();
		Questao q3 = builder.geraQuestaoDefault();
		
		return Arrays.asList(q1,q2,q3);
	}

	public QuestaoBuilder comId(Long id) {
		this.id = id;
		return this;
	}

	public QuestaoBuilder comTags(Set<Tag> tags) {
		this.tags = tags;
		return this;
	}

	public QuestaoBuilder comEstatiscaQuestao(EstatisticaQuestao estatistica) {
		this.estatistica = estatistica;
		return this;
	}

	public QuestaoBuilder comTitulo(String titulo) {
		this.titulo = titulo;
		return this;
	}

	public QuestaoBuilder comAlternativas(List<Alternativa> alternativas) {
		this.alternativa = alternativas;
		return this;
	}

	public QuestaoBuilder comAlternativaCorreta(String idAlternativaCorreta) {
		this.alternativaCorreta = idAlternativaCorreta;
		return this;
	}

}
