package br.com.caelum.geradordeprovas.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QuestaoValidator.class)
public @interface Size {
	String message() default "{br.com.caelum.validation.Size.message}";
	int value();
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}