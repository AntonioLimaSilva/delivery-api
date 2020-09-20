package br.com.luciano.delivery.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CityIdInput {

    @NotNull
    private Long id;
}
