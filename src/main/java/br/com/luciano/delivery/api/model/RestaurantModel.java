package br.com.luciano.delivery.api.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantModel {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private KitchenModel cozinha;
    private Boolean ativo;
    private EnderecoModel endereco;
}
