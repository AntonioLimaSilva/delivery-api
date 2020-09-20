package br.com.luciano.delivery.infrastructure.repository.spec;

import java.math.BigDecimal;

import br.com.luciano.delivery.domain.entity.RestaurantEntity;
import org.springframework.data.jpa.domain.Specification;

public class RestaurantSpecs {

	public static Specification<RestaurantEntity> withShippingFee() {
		return (root, query, builder) -> 
			builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
	}
	
	public static Specification<RestaurantEntity> withSimilarName(String name) {
		return (root, query, builder) ->
			builder.like(root.get("name"), "%" + name + "%");
	}
	
}
