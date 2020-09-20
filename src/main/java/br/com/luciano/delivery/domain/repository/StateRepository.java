package br.com.luciano.delivery.domain.repository;

import br.com.luciano.delivery.domain.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {

}
