package br.com.luciano.delivery.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "forma_pagamento")
public class PaymentEntity extends IdBase {
	
	@Column(name = "descricao", nullable = false)
	private String description;
	@Column(name = "numero")
	private String number;
}