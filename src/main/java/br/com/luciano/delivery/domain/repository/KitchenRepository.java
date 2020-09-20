package br.com.luciano.delivery.domain.repository;

import br.com.luciano.delivery.domain.entity.KitchenEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitchenRepository extends CustomJpaRepository<KitchenEntity, Long> {

	List<KitchenEntity> findByNameContaining(String name);
	
	Optional<KitchenEntity> findByName(String name);
	
	boolean existsByName(String name);
	
}
