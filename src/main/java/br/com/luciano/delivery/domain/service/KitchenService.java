package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.entity.KitchenEntity;
import br.com.luciano.delivery.domain.exception.CozinhaNaoEncontradaException;
import br.com.luciano.delivery.domain.exception.EntidadeEmUsoException;
import br.com.luciano.delivery.domain.repository.KitchenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KitchenService {

	private static final String MSG_KITCHEN_NOT_REMOVED = "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private KitchenRepository kitchenRepository;

	@Transactional
	public KitchenEntity save(KitchenEntity kitchen) {
		return kitchenRepository.save(kitchen);
	}

	public KitchenEntity save(Long id, KitchenEntity kitchen) {
		KitchenEntity kitchenActual = this.findByIdOrFail(id);
		BeanUtils.copyProperties(kitchen, kitchenActual, "id");
		return save(kitchenActual);
	}

	@Transactional
	public void delete(Long id) {
		try {
			kitchenRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_KITCHEN_NOT_REMOVED, id));
		}
	}

	public KitchenEntity findByIdOrFail(Long id) {
		return kitchenRepository.findById(id).orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}

	public List<KitchenEntity> findAll() {
		return this.kitchenRepository.findAll();
	}

}
