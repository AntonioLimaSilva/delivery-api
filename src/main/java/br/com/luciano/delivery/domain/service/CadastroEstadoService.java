package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.EntidadeEmUsoException;
import br.com.luciano.delivery.domain.exception.EstadoNaoEncontradoException;
import br.com.luciano.delivery.domain.model.Estado;
import br.com.luciano.delivery.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_NAO_REMOVIDO = "Estado de código %d não pode ser removido, pois está em uso";

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_ESTADO_NAO_REMOVIDO, estadoId));
		}
	}

    public Estado buscarPorId(Long estadoId) {
		return this.estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }

	public Estado salvar(Long estadoId, Estado estado) {
		Estado estadoAtual = this.buscarPorId(estadoId);

		BeanUtils.copyProperties(estado, estadoAtual, "id");

		return this.salvar(estadoAtual);
	}
}
