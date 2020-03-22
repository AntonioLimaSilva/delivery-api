package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.FormaPagamentoAssembler;
import br.com.luciano.delivery.api.assembler.FormaPagamentoDisassembler;
import br.com.luciano.delivery.api.model.FormaPagamentoModel;
import br.com.luciano.delivery.api.model.input.FormaPagamentoInput;
import br.com.luciano.delivery.domain.model.FormaPagamento;
import br.com.luciano.delivery.domain.service.CadastroFormaPagamentoService;
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
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoAssembler formaPagamentoAssembler;

    @Autowired
    private FormaPagamentoDisassembler formaPagamentoDisassembler;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @PostMapping
    public ResponseEntity<FormaPagamentoModel> adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoDisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = this.cadastroFormaPagamentoService.salvar(formaPagamento);

        FormaPagamentoModel formaPagamentoModel = this.formaPagamentoAssembler.toModel(formaPagamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoModel);
    }

    @GetMapping
    public List<FormaPagamentoModel> listarTodos() {
        return this.cadastroFormaPagamentoService.buscarTodas().stream()
                .map(f -> this.formaPagamentoAssembler.toModel(f))
                .collect(Collectors.toList());
    }

    @PutMapping("/{idPagamento}")
    public ResponseEntity<FormaPagamentoModel> editar(@PathVariable Long idPagamento, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = this.formaPagamentoDisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = this.cadastroFormaPagamentoService.atualizar(idPagamento, formaPagamento);
        FormaPagamentoModel formaPagamentoModel = this.formaPagamentoAssembler.toModel(formaPagamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoModel);
    }

    @DeleteMapping("/{idPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long idPagamento) {
        this.cadastroFormaPagamentoService.excluir(idPagamento);
    }

}
