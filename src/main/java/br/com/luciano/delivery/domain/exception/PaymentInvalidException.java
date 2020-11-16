package br.com.luciano.delivery.domain.exception;

public class PaymentInvalidException extends BusinessException {

    private static final String MESSAGE = "Forma de pagamento inválida";

    public PaymentInvalidException() {
        this(MESSAGE);
    }

    public PaymentInvalidException(String message) {
        super(message);
    }
}
