package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.KitchenAssembler;
import br.com.luciano.delivery.api.assembler.KitchenDisassembler;
import br.com.luciano.delivery.api.model.CozinhaModel;
import br.com.luciano.delivery.api.model.input.CozinhaInput;
import br.com.luciano.delivery.domain.model.Cozinha;
import br.com.luciano.delivery.domain.service.KitchenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Cozinhas")
@RestController
@RequestMapping(value = "/cozinhas")
public class KitchenController {
	
	@Autowired
	private KitchenService cadastroCozinha;

	@Autowired
	private KitchenAssembler kitchenAssembler;

	@Autowired
	private KitchenDisassembler kitchenDisassembler;
	
	@GetMapping
	public List<CozinhaModel> searchAll() {
		return cadastroCozinha.buscarTodas().stream()
				.map(c -> this.kitchenAssembler.toModel(c))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{cozinhaId}")
	public CozinhaModel searchBy(@PathVariable Long cozinhaId) {
		return this.kitchenAssembler.toModel(cadastroCozinha.buscarPorId(cozinhaId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel create(@RequestBody @Valid CozinhaInput cozinhaInput) {

		Cozinha cozinha = this.kitchenDisassembler.toDomainObject(cozinhaInput);
		cozinha = cadastroCozinha.salvar(cozinha);

		return this.kitchenAssembler.toModel(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public CozinhaModel update(@PathVariable Long cozinhaId, @RequestBody CozinhaInput cozinhaInput) {

		Cozinha cozinha = this.kitchenDisassembler.toDomainObject(cozinhaInput);
		cozinha = cadastroCozinha.salvar(cozinhaId, cozinha);

		return this.kitchenAssembler.toModel(cozinha);
	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir(cozinhaId);
	}
}
