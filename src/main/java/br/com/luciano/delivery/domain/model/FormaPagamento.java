package br.com.luciano.delivery.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class FormaPagamento extends IdBase {
	
	@Column(nullable = false)
	private String descricao;
	
}