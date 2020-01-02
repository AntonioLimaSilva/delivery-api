package br.com.luciano.delivery.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MultipleValidation.class })
public @interface Multiple {

    String message() default "múltiplo inválido";

    Class<?> [] groups() default {};

    Class<? extends Payload> [] payload() default {};

    int number();
}
