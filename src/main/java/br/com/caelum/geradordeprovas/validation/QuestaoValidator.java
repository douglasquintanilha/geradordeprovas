package br.com.caelum.geradordeprovas.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.caelum.geradordeprovas.models.Alternativa;

public class QuestaoValidator implements ConstraintValidator<Size, List<Alternativa>>{ 
	
	
	private int tamanho;
	
	@Override
	public void initialize(Size constrainAnnotation) {
		this.tamanho = constrainAnnotation.value();
	}

	@Override
	public boolean isValid(List<Alternativa> alternativas, ConstraintValidatorContext ctx) {
		if(alternativas.size() != this.tamanho){
			return false;
		}
		return true;
	}
	
}