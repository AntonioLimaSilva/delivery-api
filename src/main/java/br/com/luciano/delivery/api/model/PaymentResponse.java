package br.com.luciano.delivery.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {

    private String message;
    private boolean isAuthorized;

}
