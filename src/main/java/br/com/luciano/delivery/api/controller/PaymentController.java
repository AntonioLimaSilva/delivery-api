package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.PaymentAssembler;
import br.com.luciano.delivery.api.assembler.PaymentDisassembler;
import br.com.luciano.delivery.api.model.FormaPagamentoModel;
import br.com.luciano.delivery.api.model.input.FormaPagamentoInput;
import br.com.luciano.delivery.domain.model.FormaPagamento;
import br.com.luciano.delivery.domain.service.PaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Formas de pagamento")
@RestController
@RequestMapping("/pagamentos")
public class PaymentController {

    @Autowired
    private PaymentAssembler paymentAssembler;

    @Autowired
    private PaymentDisassembler paymentDisassembler;

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<FormaPagamentoModel> create(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = paymentDisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = this.paymentService.salvar(formaPagamento);

        FormaPagamentoModel formaPagamentoModel = this.paymentAssembler.toModel(formaPagamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoModel);
    }

    @GetMapping
    public List<FormaPagamentoModel> searchAll() {
        return this.paymentService.buscarTodas().stream()
                .map(f -> this.paymentAssembler.toModel(f))
                .collect(Collectors.toList());
    }

    @PutMapping("/{idPagamento}")
    public ResponseEntity<FormaPagamentoModel> update(@PathVariable Long idPagamento, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = this.paymentDisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = this.paymentService.atualizar(idPagamento, formaPagamento);
        FormaPagamentoModel formaPagamentoModel = this.paymentAssembler.toModel(formaPagamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoModel);
    }

    @DeleteMapping("/{idPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long idPagamento) {
        this.paymentService.excluir(idPagamento);
    }

}
