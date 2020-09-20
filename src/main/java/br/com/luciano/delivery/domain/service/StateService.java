package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.entity.StateEntity;
import br.com.luciano.delivery.domain.exception.EntidadeEmUsoException;
import br.com.luciano.delivery.domain.exception.EstadoNaoEncontradoException;
import br.com.luciano.delivery.domain.repository.StateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StateService {

	private static final String MSG_ESTADO_NAO_REMOVIDO = "Estado de código %d não pode ser removido, pois está em uso";

	@Autowired
	private StateRepository stateRepository;

	@Transactional
	public StateEntity save(StateEntity stateEntity) {
		return stateRepository.save(stateEntity);
	}

	@Transactional
	public void delete(Long id) {
		try {
			stateRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_ESTADO_NAO_REMOVIDO, id));
		}
	}

    public StateEntity findByIdOrFail(Long id) {
		return this.stateRepository.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradoException(id));
    }

    @Transactional
	public StateEntity save(Long id, StateEntity stateEntity) {
		StateEntity stateEntityActual = this.findByIdOrFail(id);

		BeanUtils.copyProperties(stateEntity, stateEntityActual, "id");

		return this.save(stateEntityActual);
	}

	public List<StateEntity> findAll() {
		return this.stateRepository.findAll();
	}
}
