package br.com.luciano.delivery.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ValidationRestaurantException extends RuntimeException {

    private final transient BindingResult bindingResult;

}
