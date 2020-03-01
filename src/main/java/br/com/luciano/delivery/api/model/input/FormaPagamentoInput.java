package br.com.luciano.delivery.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FormaPagamentoInput {

    @NotBlank
    private String descricao;

}
