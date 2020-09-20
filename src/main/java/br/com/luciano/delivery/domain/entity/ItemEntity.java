package br.com.luciano.delivery.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_pedido")
public class ItemEntity {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "preco_unitario")
	private BigDecimal unitPrice;
	@Column(name = "preco_total")
	private BigDecimal totalPrice;
	@Column(name = "quantidade")
	private Integer quantity;
	@Column(name = "observacao")
	private String note;
	@ManyToOne
	@JoinColumn(name="pedido_id", nullable = false)
	private OrderEntity order;
	@ManyToOne
	@JoinColumn(name="produto_id", nullable = false)
	private ProductEntity product;

}
