package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.CountryAssembler;
import br.com.luciano.delivery.api.assembler.CountryDissembler;
import br.com.luciano.delivery.api.model.EstadoModel;
import br.com.luciano.delivery.api.model.input.EstadoInput;
import br.com.luciano.delivery.domain.model.Estado;
import br.com.luciano.delivery.domain.service.CountryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Estados")
@RestController
@RequestMapping("/estados")
public class CountryController {
	
	@Autowired
	private CountryService countryService;

	@Autowired
	private CountryAssembler countryAssembler;

	@Autowired
	private CountryDissembler countryDissembler;
	
	@GetMapping
	public List<EstadoModel> searchAll() {
		return countryService.buscarTodos().stream()
				.map(e -> this.countryAssembler.toModel(e))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{estadoId}")
	public EstadoModel searchBy(@PathVariable Long estadoId) {
		return this.countryAssembler.toModel(countryService.buscarPorId(estadoId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel create(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = this.countryDissembler.toDomainObject(estadoInput);
		estado = countryService.salvar(estado);

		return this.countryAssembler.toModel(estado);
	}
	
	@PutMapping("/{estadoId}")
	public EstadoModel update(@PathVariable Long estadoId, @RequestBody EstadoInput estadoInput) {
		Estado estado = this.countryDissembler.toDomainObject(estadoInput);
		estado = this.countryService.salvar(estadoId, estado);

		return this.countryAssembler.toModel(estado);
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long estadoId) {
		this.countryService.excluir(estadoId);
	}
	
}
