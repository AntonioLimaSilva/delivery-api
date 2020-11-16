package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.entity.CityEntity;
import br.com.luciano.delivery.domain.entity.StateEntity;
import br.com.luciano.delivery.domain.exception.CityNotFoundException;
import br.com.luciano.delivery.domain.exception.EntityInUseException;
import br.com.luciano.delivery.domain.exception.StateNotFoundException;
import br.com.luciano.delivery.domain.exception.BusinessException;
import br.com.luciano.delivery.domain.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

	private static final Logger log = LoggerFactory.getLogger(CityService.class);

	private static final String MESSAGE_CITY = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateService stateService;

	@Transactional
	public CityEntity save(CityEntity cityEntity) {
		Long stateId = cityEntity.getState().getId();

		StateEntity stateEntity;
		try {
			stateEntity = stateService.findByIdOrFail(stateId);
		} catch (StateNotFoundException ex) {
			throw new BusinessException(ex.getMessage(), ex);
		}
		
		cityEntity.setState(stateEntity);
		
		return cityRepository.save(cityEntity);
	}

	@Transactional
	public void delete(Long id) {
		try {
			cityRepository.deleteById(id);
			cityRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new CityNotFoundException(id);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MESSAGE_CITY, id));
		}
	}

    public CityEntity findByIdOrFail(Long id) {
		return this.cityRepository.findById(id)
				.orElseThrow(() -> new CityNotFoundException(id));
    }

    @Transactional
	public CityEntity save(Long cidadeId, CityEntity cityEntity) {
		// Podemos usar o orElse(null) também, que retorna a instância de cidade
		// dentro do Optional, ou null, caso ele esteja vazio,
		// mas nesse caso, temos a responsabilidade de tomar cuidado com NullPointerException
		CityEntity cityEntityActual = this.findByIdOrFail(cidadeId);

		BeanUtils.copyProperties(cityEntity, cityEntityActual, "id");

		return this.save(cityEntityActual);
	}

    public List<CityEntity> findAll() {
		log.debug("Obtendo listagem de cidades");
		return this.cityRepository.findAll();
    }
}
