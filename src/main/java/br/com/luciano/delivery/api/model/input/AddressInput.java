package br.com.luciano.delivery.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddressInput {

    @ApiModelProperty(example = "06401015", position = 1)
    @NotBlank
    private String cep;
    @ApiModelProperty(example = "Nubia", position = 2)
    @NotBlank
    private String street;
    @ApiModelProperty(example = "190", position = 3)
    @NotBlank
    private String number;
    @ApiModelProperty(example = "Casa", position = 4)
    private String complement;
    @ApiModelProperty(example = "Av. Dom sebastião", position = 5)
    @NotBlank
    private String neighborhood;
    @ApiModelProperty(position = 6)
    @Valid
    @NotNull
    private CityIdInput city;
}
