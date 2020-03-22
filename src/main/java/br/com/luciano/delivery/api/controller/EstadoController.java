package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.EstadoAssembler;
import br.com.luciano.delivery.api.assembler.EstadoDissembler;
import br.com.luciano.delivery.api.model.EstadoModel;
import br.com.luciano.delivery.api.model.input.EstadoInput;
import br.com.luciano.delivery.domain.model.Estado;
import br.com.luciano.delivery.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private CadastroEstadoService cadastroEstado;

	@Autowired
	private EstadoAssembler estadoAssembler;

	@Autowired
	private EstadoDissembler estadoDissembler;
	
	@GetMapping
	public List<EstadoModel> listar() {
		return cadastroEstado.buscarTodos().stream()
				.map(e -> this.estadoAssembler.toModel(e))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
		return this.estadoAssembler.toModel(cadastroEstado.buscarPorId(estadoId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = this.estadoDissembler.toDomainObject(estadoInput);
		estado = cadastroEstado.salvar(estado);

		return this.estadoAssembler.toModel(estado);
	}
	
	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody EstadoInput estadoInput) {
		Estado estado = this.estadoDissembler.toDomainObject(estadoInput);
		estado = this.cadastroEstado.salvar(estadoId, estado);

		return this.estadoAssembler.toModel(estado);
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		this.cadastroEstado.excluir(estadoId);
	}
	
}
