package br.com.luciano.delivery.domain.entity;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produto")
public class ProductEntity {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="nome", nullable = false)
	private String name;
	
	@Column(name="descricao", nullable = false)
	private String description;
	
	@Column(name="preco", nullable = false)
	private BigDecimal price;
	
	@Column(name="ativo", nullable = false)
	private Boolean active;

	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private RestaurantEntity restaurant;

}