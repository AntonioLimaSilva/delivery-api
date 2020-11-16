package br.com.luciano.delivery.domain.exception;

public class GroupNotFoundException extends EntityNotFoundException {

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(Long id) {
        this(String.format("Grupo não encontrado com id %d ", id));
    }
}
