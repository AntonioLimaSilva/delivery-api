package br.com.luciano.delivery.domain.exception;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long cityId) {
        this(String.format("Não existe um cadastro de usuário com código %d", cityId));
    }
}
