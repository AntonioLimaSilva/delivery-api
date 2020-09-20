package br.com.luciano.delivery.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedido")
public class OrderEntity {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal subtotal;
	@Column(name = "taxa_frete")
	private BigDecimal shippingFee;
	@Column(name = "valor_total")
	private BigDecimal amount;

	@Embedded
	private Address address;
	
	private StatusOrder status;
	
	@CreationTimestamp
	private LocalDateTime createAt;
	private LocalDateTime confirmationAt;
	private LocalDateTime cancellationAt;
	private LocalDateTime deliveryAt;
	
	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id", nullable = false)
	private PaymentEntity payment;
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private RestaurantEntity restaurant;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private UserEntity user;
	
	@OneToMany(mappedBy = "order")
	private List<ItemEntity> items = new ArrayList<>();

}
