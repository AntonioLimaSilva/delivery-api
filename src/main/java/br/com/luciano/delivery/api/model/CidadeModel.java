package br.com.luciano.delivery.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CidadeModel {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Fortaleza")
    private String nome;

    private EstadoModel estado;

}
