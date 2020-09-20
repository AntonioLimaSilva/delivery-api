package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.*;
import br.com.luciano.delivery.domain.model.Cidade;
import br.com.luciano.delivery.domain.model.Cozinha;
import br.com.luciano.delivery.domain.model.Restaurante;
import br.com.luciano.delivery.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private KitchenService kitchenService;

	@Autowired
	private CityService cityService;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Cozinha cozinha = kitchenService.buscarPorId(restaurante.getCozinha().getId());
		Cidade cidade = cityService.buscarPorId(restaurante.getEndereco().getCidade().getId());

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);
		
		return restauranteRepository.save(restaurante);
	}

    public Restaurante buscarPorId(Long restauranteId) {
		return this.restauranteRepository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    @Transactional
	public Restaurante salvar(Long restauranteId, Restaurante restaurante) {
		Restaurante restauranteAtual = this.buscarPorId(restauranteId);

		BeanUtils.copyProperties(restaurante, restauranteAtual,
				"id", "formasPagamento", "dataCadastro", "produtos");

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

	public List<Restaurante> buscarTodos() {
		return this.restauranteRepository.findAll();
	}
}
