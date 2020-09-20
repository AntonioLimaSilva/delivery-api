package br.com.luciano.delivery.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CityInput {

    @ApiModelProperty(example = "Fortaleza", required = true)
    @NotBlank
    private String name;
    @Valid
    @NotNull
    private CountryIdInput country;
}
