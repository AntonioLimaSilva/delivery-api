package br.com.luciano.delivery.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long cityId) {
        this(String.format("Não existe um cadastro de produto com código %d", cityId));
    }
}
