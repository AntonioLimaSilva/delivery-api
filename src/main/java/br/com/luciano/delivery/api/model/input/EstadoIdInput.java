package br.com.luciano.delivery.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EstadoIdInput {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;

}
