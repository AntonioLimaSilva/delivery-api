package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.CozinhaNaoEncontradaException;
import br.com.luciano.delivery.domain.exception.EntidadeNaoEncontradaException;
import br.com.luciano.delivery.domain.exception.NegocioException;
import br.com.luciano.delivery.domain.exception.RestauranteNaoEncontradoException;
import br.com.luciano.delivery.domain.model.Cozinha;
import br.com.luciano.delivery.domain.model.Restaurante;
import br.com.luciano.delivery.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha;
		try {
			cozinha = cadastroCozinhaService.buscarPorId(cozinhaId);
		} catch (CozinhaNaoEncontradaException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}

    public Restaurante buscarPorId(Long restauranteId) {
		return this.restauranteRepository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    @Transactional
	public Restaurante salvar(Long restauranteId, Restaurante restaurante) {
		Restaurante restauranteAtual = this.buscarPorId(restauranteId);

		BeanUtils.copyProperties(restaurante, restauranteAtual,
				"id", "formasPagamento", "endereco", "dataCadastro", "produtos");

		return this.salvar(restauranteAtual);
	}

	@Transactional
	public void ativar(Long id) {
		Restaurante restaurante = this.buscarPorId(id);
		restaurante.setAtivo(true);
	}

	@Transactional
	public void inativar(Long id) {
		Restaurante restaurante = this.buscarPorId(id);
		restaurante.setAtivo(false);
	}
}
