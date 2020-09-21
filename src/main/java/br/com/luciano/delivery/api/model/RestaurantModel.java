package br.com.luciano.delivery.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantModel {

    private Long id;
    private String nome;
    private BigDecimal shippingFee;
    private KitchenModel kitchen;
    private Boolean active;
    private AddressModel address;
}
