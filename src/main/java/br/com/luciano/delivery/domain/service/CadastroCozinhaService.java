package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.CozinhaNaoEncontradaException;
import br.com.luciano.delivery.domain.exception.EntidadeEmUsoException;
import br.com.luciano.delivery.domain.model.Cozinha;
import br.com.luciano.delivery.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_NAO_REMOVIDO = "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public Cozinha salvar(Long cozinhaId, Cozinha cozinha) {
		Cozinha cozinhaAtual = this.buscarPorId(cozinhaId);
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		return salvar(cozinhaAtual);
	}

	@Transactional
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_COZINHA_NAO_REMOVIDO, cozinhaId));
		}
	}

	public Cozinha buscarPorId(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}

}
