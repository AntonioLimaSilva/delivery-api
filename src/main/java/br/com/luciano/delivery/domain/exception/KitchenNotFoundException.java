package br.com.luciano.delivery.domain.exception;

public class KitchenNotFoundException extends EntityNotFoundException {

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long idCozinha) {
        this(String.format("Não existe um cadastro de cozinha com código %d", idCozinha));
    }
}
