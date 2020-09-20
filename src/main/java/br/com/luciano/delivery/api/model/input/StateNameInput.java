package br.com.luciano.delivery.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StateNameInput {

    @ApiModelProperty(example = "Ceará")
    @NotNull
    private String name;
}
