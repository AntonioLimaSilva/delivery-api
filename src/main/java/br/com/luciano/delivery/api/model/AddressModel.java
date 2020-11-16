package br.com.luciano.delivery.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

    private String cep;
    private String street;
    private String number;
    private String note;
    private String neighborhood;
    private CityResumeModel city;
}
