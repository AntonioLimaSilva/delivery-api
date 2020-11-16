package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.entity.UserEntity;
import br.com.luciano.delivery.domain.exception.UserNotFoundException;
import br.com.luciano.delivery.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity findOrFail(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

}
