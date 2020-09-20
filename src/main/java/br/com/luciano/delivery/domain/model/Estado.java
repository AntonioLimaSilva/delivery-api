package br.com.luciano.delivery.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Estado extends IdBase {

	@Column(nullable = false)
	private String nome;
}