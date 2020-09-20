package br.com.luciano.delivery.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cozinha")
public class KitchenEntity extends IdBase {

	@Column(name="nome", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "kitchen")
	private List<RestaurantEntity> restaurants = new ArrayList<>();
}
