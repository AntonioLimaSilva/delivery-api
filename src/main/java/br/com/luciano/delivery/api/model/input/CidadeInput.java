package br.com.luciano.delivery.api.model.input;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CidadeInput {

    @NotBlank
    private String nome;
    @Valid
    @NotNull
    private EstadoInput estado;
}
