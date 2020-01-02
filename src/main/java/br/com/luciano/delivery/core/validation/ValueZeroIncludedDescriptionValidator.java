package br.com.luciano.delivery.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Objects;

public class ValueZeroIncludedDescriptionValidator implements ConstraintValidator<ValueZeroIncludedDescription, Object> {

    private String valueField;
    private String descriptionField;
    private String description;

    @Override
    public void initialize(ValueZeroIncludedDescription constraintAnnotation) {
        this.valueField = constraintAnnotation.valueField();
        this.descriptionField = constraintAnnotation.descriptionField();
        this.description = constraintAnnotation.description();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (object != null) {
            try {
                BigDecimal taxafrete = (BigDecimal) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(object.getClass(), valueField))
                        .getReadMethod().invoke(object);

                String nome = (String) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(object.getClass(), descriptionField))
                        .getReadMethod().invoke(object);


                isValid = BigDecimal.ZERO.compareTo(taxafrete) == 0
                        && nome.toLowerCase().contains(description != null ? description.toLowerCase() : "");

            } catch (Exception e) {
                throw new ValidationException(e);
            }
        }

        return isValid;
    }
}
