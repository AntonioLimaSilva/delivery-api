package br.com.luciano.delivery.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValueZeroIncludedDescriptionValidator.class })
public @interface ValueZeroIncludedDescription {

    String message() default "Taxa frete deve conter descrição frete grátis";

    Class<?> [] groups() default {};

    Class<? extends Payload> [] payload() default {};
    
    String valueField();

    String descriptionField();

    String description();

}
