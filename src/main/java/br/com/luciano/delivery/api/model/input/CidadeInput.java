package br.com.luciano.delivery.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CidadeInput {

    @ApiModelProperty(example = "Fortaleza", required = true)
    @NotBlank
    private String nome;
    @Valid
    @NotNull
    private EstadoIdInput estado;
}
