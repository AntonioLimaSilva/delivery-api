package br.com.luciano.delivery.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public FormaPagamentoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoNaoEncontradaException(Long idFormaPagamento) {
        this(String.format("Não existe um cadastro de forma de pagamento com código %d", idFormaPagamento));
    }
}
