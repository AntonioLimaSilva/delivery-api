package br.com.luciano.delivery.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {

    public StateNotFoundException(String mensagem) {
        super(mensagem);
    }

    public StateNotFoundException(Long stateId) {
        this(String.format("Não existe um cadastro de estado com código %d", stateId));
    }
}
