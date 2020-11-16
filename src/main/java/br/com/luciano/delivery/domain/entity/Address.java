package br.com.luciano.delivery.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
public class Address {

	@Column(name = "endereco_cep")
	private String cep;
	@Column(name = "endereco_logradouro")
	private String street;
	@Column(name = "endereco_numero")
	private String number;
	@Column(name = "endereco_complemento")
	private String complement;
	@Column(name = "endereco_bairro")
	private String neighborhood;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_cidade_id")
	private CityEntity city;
	
}
