package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.PaymentAssembler;
import br.com.luciano.delivery.api.assembler.PaymentDisassembler;
import br.com.luciano.delivery.api.model.PaymentModel;
import br.com.luciano.delivery.api.model.input.PaymentDescriptionInput;
import br.com.luciano.delivery.domain.entity.PaymentEntity;
import br.com.luciano.delivery.domain.service.PaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Payments")
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentAssembler paymentAssembler;

    @Autowired
    private PaymentDisassembler paymentDisassembler;

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentModel> create(@RequestBody @Valid PaymentDescriptionInput paymentDescriptionInput) {
        PaymentEntity paymentEntity = paymentDisassembler.toDomainObject(paymentDescriptionInput);

        paymentEntity = this.paymentService.save(paymentEntity);

        PaymentModel paymentModel = this.paymentAssembler.toModel(paymentEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentModel);
    }

    @GetMapping
    public List<PaymentModel> searchAll() {
        return this.paymentService.findAll().stream()
                .map(f -> this.paymentAssembler.toModel(f))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentModel> update(@PathVariable Long id, @RequestBody @Valid PaymentDescriptionInput paymentDescriptionInput) {
        PaymentEntity paymentEntity = this.paymentDisassembler.toDomainObject(paymentDescriptionInput);

        paymentEntity = this.paymentService.update(id, paymentEntity);
        PaymentModel paymentModel = this.paymentAssembler.toModel(paymentEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        this.paymentService.delete(id);
    }

}
