package br.com.luciano.delivery.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.luciano.delivery.domain.entity.RestaurantEntity;

public interface RestaurantRepositoryQueries {

	List<RestaurantEntity> find(String nome, BigDecimal initialShippingFee, BigDecimal finalShippingFee);
	
	List<RestaurantEntity> findWithFreeShipping(String nome);

}