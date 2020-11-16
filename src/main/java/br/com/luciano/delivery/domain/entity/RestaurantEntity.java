package br.com.luciano.delivery.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "restaurante")
public class RestaurantEntity extends IdBase {

	@Column(name="nome", nullable = false)
	private String name;

	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal shippingFee;

	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private KitchenEntity kitchen;

	@Embedded
	private Address address;

	@CreationTimestamp
	@Column(name="data_cadastro", nullable = false, columnDefinition = "datetime")
	private OffsetDateTime createAt;

	@UpdateTimestamp
	@Column(name="data_atualizacao", nullable = false, columnDefinition = "datetime")
	private OffsetDateTime updateAt;
	
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<PaymentEntity> payments = new ArrayList<>();
	
	@OneToMany(mappedBy = "restaurant")
	private List<ProductEntity> products = new ArrayList<>();

	@Column(name = "ativo")
	private Boolean active;
	
}
