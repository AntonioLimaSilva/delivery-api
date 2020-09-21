package br.com.luciano.delivery.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pedido")
public class OrderEntity extends IdBase {

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
