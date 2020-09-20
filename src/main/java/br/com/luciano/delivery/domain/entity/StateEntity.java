package br.com.luciano.delivery.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "estado")
public class StateEntity extends IdBase {

	@Column(name = "nome", nullable = false)
	private String name;
}