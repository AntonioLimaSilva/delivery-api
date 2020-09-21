package br.com.luciano.delivery.api.model;

import lombok.Data;

@Data
public class AddressModel {

    private String cep;
    private String addressName;
    private String number;
    private String note;
    private String district;
    private CityResumeModel city;
}
