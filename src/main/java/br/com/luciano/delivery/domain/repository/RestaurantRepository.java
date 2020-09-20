package br.com.luciano.delivery.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import br.com.luciano.delivery.domain.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository
		extends CustomJpaRepository<RestaurantEntity, Long>, RestaurantRepositoryQueries,
		JpaSpecificationExecutor<RestaurantEntity> {

	@Query("from RestaurantEntity r join fetch r.kitchen")
	List<RestaurantEntity> findAll();
	
	List<RestaurantEntity> queryByShippingFeeBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<RestaurantEntity> consultarPorNome(String name, @Param("id") Long kitchenId);
	
//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
	
	Optional<RestaurantEntity> findFirstRestaurantByNameContaining(String name);
	
	List<RestaurantEntity> findTop2ByNameContaining(String name);
	
	int countByKitchenId(Long id);
	
}
