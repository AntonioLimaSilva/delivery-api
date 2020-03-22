package br.com.luciano.delivery.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EstadoModel {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Ceará")
    private String nome;
}
