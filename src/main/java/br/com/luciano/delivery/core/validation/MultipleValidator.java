package br.com.luciano.delivery.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultipleValidator implements ConstraintValidator<Multiple, Number> {

    private int numberMultiple;

    @Override
    public void initialize(Multiple constraintAnnotation) {
        this.numberMultiple = constraintAnnotation.number();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (value != null) {

            BigDecimal valueBigDecimal = BigDecimal.valueOf(value.doubleValue());
            BigDecimal valueMultiple = BigDecimal.valueOf(numberMultiple);
            BigDecimal dif = valueBigDecimal.remainder(valueMultiple);

            isValid = BigDecimal.ZERO.compareTo(dif) == 0;
        }

        return isValid;
    }
}
