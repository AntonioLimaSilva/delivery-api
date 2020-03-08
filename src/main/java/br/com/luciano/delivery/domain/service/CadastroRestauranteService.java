package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.*;
import br.com.luciano.delivery.domain.model.Cidade;
import br.com.luciano.delivery.domain.model.Cozinha;
import br.com.luciano.delivery.domain.model.Endereco;
import br.com.luciano.delivery.domain.model.Restaurante;
import br.com.luciano.delivery.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Cozinha cozinha = cadastroCozinhaService.buscarPorId(restaurante.getCozinha().getId());
		Cidade cidade = cadastroCidadeService.buscarPorId(restaurante.getEndereco().getCidade().getId());

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
