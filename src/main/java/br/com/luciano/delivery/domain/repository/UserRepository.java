package br.com.luciano.delivery.domain.repository;

import br.com.luciano.delivery.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
