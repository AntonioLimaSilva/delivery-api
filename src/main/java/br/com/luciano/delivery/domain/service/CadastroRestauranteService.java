package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.repository.RestauranteRepository;
import br.com.luciano.delivery.domain.exception.NegocioException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luciano.delivery.domain.exception.EntidadeNaoEncontradaException;
import br.com.luciano.delivery.domain.model.Cozinha;
import br.com.luciano.delivery.domain.model.Restaurante;

@Service
public class CadastroRestauranteService {

	private static final String ENTIDADE_NAO_ENCONTRO = "Restaurante não encontrado com id: %d";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha;
		try {
			cozinha = cadastroCozinhaService.buscarPorId(cozinhaId);
		} catch (EntidadeNaoEncontradaException ex) {
			throw new NegocioException(ex.getMessage());
		}
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}

    public Restaurante buscarPorId(Long restauranteId) {
		return this.restauranteRepository.findById(restauranteId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(ENTIDADE_NAO_ENCONTRO, restauranteId)));
    }

	public Restaurante salvar(Long restauranteId, Restaurante restaurante) {
		Restaurante restauranteAtual = this.buscarPorId(restauranteId);

		BeanUtils.copyProperties(restaurante, restauranteAtual,
				"id", "formasPagamento", "endereco", "dataCadastro", "produtos");

		return this.salvar(restauranteAtual);
	}
}
