package br.com.luciano.delivery.domain.exception;

public class PaymentNotFoundException extends EntityNotFoundException {

    public PaymentNotFoundException(String message) {
        super(message);
    }

    public PaymentNotFoundException(Long idFormaPagamento) {
        this(String.format("Não existe um cadastro de forma de pagamento com código %d", idFormaPagamento));
    }
}
