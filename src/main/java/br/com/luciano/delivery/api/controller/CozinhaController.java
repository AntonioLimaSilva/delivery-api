package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.CozinhaAssembler;
import br.com.luciano.delivery.api.assembler.CozinhaDisassembler;
import br.com.luciano.delivery.api.model.CozinhaModel;
import br.com.luciano.delivery.api.model.input.CozinhaInput;
import br.com.luciano.delivery.domain.model.Cozinha;
import br.com.luciano.delivery.domain.service.CadastroCozinhaService;
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
public class CozinhaController {
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CozinhaAssembler cozinhaAssembler;

	@Autowired
	private CozinhaDisassembler cozinhaDisassembler;
	
	@GetMapping
	public List<CozinhaModel> listar() {
		return cadastroCozinha.buscarTodas().stream()
				.map(c -> this.cozinhaAssembler.toModel(c))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		return this.cozinhaAssembler.toModel(cadastroCozinha.buscarPorId(cozinhaId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {

		Cozinha cozinha = this.cozinhaDisassembler.toDomainObject(cozinhaInput);
		cozinha = cadastroCozinha.salvar(cozinha);

		return this.cozinhaAssembler.toModel(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody CozinhaInput cozinhaInput) {

		Cozinha cozinha = this.cozinhaDisassembler.toDomainObject(cozinhaInput);
		cozinha = cadastroCozinha.salvar(cozinhaId, cozinha);

		return this.cozinhaAssembler.toModel(cozinha);
	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir(cozinhaId);
	}
}
