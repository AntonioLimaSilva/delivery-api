package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.EntidadeEmUsoException;
import br.com.luciano.delivery.domain.exception.EstadoNaoEncontradoException;
import br.com.luciano.delivery.domain.repository.CidadeRepository;
import br.com.luciano.delivery.domain.exception.EntidadeNaoEncontradaException;
import br.com.luciano.delivery.domain.exception.NegocioException;
import br.com.luciano.delivery.domain.model.Cidade;
import br.com.luciano.delivery.domain.model.Estado;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

	private static final String MSG_ENTIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com código %d";
	private static final String MSG_CIDADE = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();

		Estado estado;
		try {
			estado = cadastroEstadoService.buscarPorId(estadoId);
		} catch (EstadoNaoEncontradoException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
		
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format(MSG_ENTIDADE_NAO_ENCONTRADA, cidadeId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_CIDADE, cidadeId));
		}
	}

    public Cidade buscarPorId(Long cidadeId) {
		return this.cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ENTIDADE_NAO_ENCONTRADA, cidadeId)));
    }

	public Cidade salvar(Long cidadeId, Cidade cidade) {
		// Podemos usar o orElse(null) também, que retorna a instância de cidade
		// dentro do Optional, ou null, caso ele esteja vazio,
		// mas nesse caso, temos a responsabilidade de tomar cuidado com NullPointerException
		Cidade cidadeAtual = this.buscarPorId(cidadeId);

		BeanUtils.copyProperties(cidade, cidadeAtual, "id");

		return this.salvar(cidadeAtual);
	}
}
