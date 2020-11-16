package br.com.luciano.delivery.api.model.input;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class RestaurantInput {

    @NotBlank
    private String nome;
    @PositiveOrZero
    @NotNull
    private BigDecimal taxaFrete;
    @NotNull
    private Boolean ativo;
    @Valid
    @NotNull
    private KitchenIdInput cozinha;
    @Valid
    @NotNull
    private AddressInput endereco;
}
