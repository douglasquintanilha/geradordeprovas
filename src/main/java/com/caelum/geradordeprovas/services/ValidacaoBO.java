package com.caelum.geradordeprovas.services;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.caelum.geradordeprovas.models.Alternativa;

public class ValidacaoBO {

	public void validar(@Valid Alternativa alternativa) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Alternativa>> validate = validator
				.validate(alternativa);
		
		for (ConstraintViolation<Alternativa> constraintViolation : validate) {
			System.out.println(constraintViolation.getMessage());
		}

	}
}
