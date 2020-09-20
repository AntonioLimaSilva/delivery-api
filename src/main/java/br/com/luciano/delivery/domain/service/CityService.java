package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.CidadeNaoEncontradaException;
import br.com.luciano.delivery.domain.exception.EntidadeEmUsoException;
import br.com.luciano.delivery.domain.exception.EstadoNaoEncontradoException;
import br.com.luciano.delivery.domain.exception.NegocioException;
import br.com.luciano.delivery.domain.model.Cidade;
import br.com.luciano.delivery.domain.model.Estado;
import br.com.luciano.delivery.domain.repository.CidadeRepository;
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

	private static final String MSG_CIDADE = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CountryService countryService;

	@Transactional
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();

		Estado estado;
		try {
			estado = countryService.buscarPorId(estadoId);
		} catch (EstadoNaoEncontradoException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
		
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}

	@Transactional
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
			cidadeRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(cidadeId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_CIDADE, cidadeId));
		}
	}

    public Cidade buscarPorId(Long cidadeId) {
		return this.cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }

    @Transactional
	public Cidade salvar(Long cidadeId, Cidade cidade) {
		// Podemos usar o orElse(null) também, que retorna a instância de cidade
		// dentro do Optional, ou null, caso ele esteja vazio,
		// mas nesse caso, temos a responsabilidade de tomar cuidado com NullPointerException
		Cidade cidadeAtual = this.buscarPorId(cidadeId);

		BeanUtils.copyProperties(cidade, cidadeAtual, "id");

		return this.salvar(cidadeAtual);
	}

    public List<Cidade> buscarTodas() {
		log.debug("Obtendo listagem de cidades");
		return this.cidadeRepository.findAll();
    }
}
