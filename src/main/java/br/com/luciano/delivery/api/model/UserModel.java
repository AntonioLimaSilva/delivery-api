package br.com.luciano.delivery.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "João da Silva")
    private String name;

    @ApiModelProperty(example = "joao.ger@algafood.com.br")
    private String email;
}
