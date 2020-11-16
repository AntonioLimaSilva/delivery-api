package br.com.luciano.delivery.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "item_pedido")
public class ItemEntity extends IdBase {

	@Column(name = "preco_unitario")
	private BigDecimal unitPrice;
	@Column(name = "preco_total")
	private BigDecimal totalPrice;
	@Column(name = "quantidade")
	private Integer quantity;
	@Column(name = "observacao")
	private String note;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pedido_id", nullable = false)
	private OrderEntity order;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="produto_id", nullable = false)
	private ProductEntity product;

}
