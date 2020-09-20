package br.com.luciano.delivery.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cidade")
public class CityEntity extends IdBase {

	@Column(name = "nome", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private StateEntity state;

}