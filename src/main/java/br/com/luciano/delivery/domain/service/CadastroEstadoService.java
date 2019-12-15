package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.EntidadeEmUsoException;
import br.com.luciano.delivery.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.luciano.delivery.domain.exception.EntidadeNaoEncontradaException;
import br.com.luciano.delivery.domain.model.Estado;

@Service
public class CadastroEstadoService {

	public static final String ENTIDADE_NAO_ENCONTRADA = "Não existe um cadastro de estado com código %d";
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format(ENTIDADE_NAO_ENCONTRADA, estadoId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
		}
	}

    public Estado buscarPorId(Long estadoId) {
		return this.estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(ENTIDADE_NAO_ENCONTRADA, estadoId)));
    }

	public Estado salvar(Long estadoId, Estado estado) {
		Estado estadoAtual = this.buscarPorId(estadoId);

		BeanUtils.copyProperties(estado, estadoAtual, "id");

		return this.salvar(estadoAtual);
	}
}
