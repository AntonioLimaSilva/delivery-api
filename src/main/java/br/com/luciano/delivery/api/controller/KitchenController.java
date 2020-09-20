package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.KitchenAssembler;
import br.com.luciano.delivery.api.assembler.KitchenDisassembler;
import br.com.luciano.delivery.api.model.KitchenModel;
import br.com.luciano.delivery.api.model.input.KitchenIdInput;
import br.com.luciano.delivery.domain.entity.KitchenEntity;
import br.com.luciano.delivery.domain.service.KitchenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Kitchens")
@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {
	
	@Autowired
	private KitchenService cadastroCozinha;

	@Autowired
	private KitchenAssembler kitchenAssembler;

	@Autowired
	private KitchenDisassembler kitchenDisassembler;
	
	@GetMapping
	public List<KitchenModel> searchAll() {
		return cadastroCozinha.findAll().stream()
				.map(c -> this.kitchenAssembler.toModel(c))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public KitchenModel searchBy(@PathVariable Long id) {
		return this.kitchenAssembler.toModel(cadastroCozinha.findByIdOrFail(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public KitchenModel create(@RequestBody @Valid KitchenIdInput kitchenIdInput) {

		KitchenEntity kitchen = this.kitchenDisassembler.toDomainObject(kitchenIdInput);
		kitchen = cadastroCozinha.save(kitchen);

		return this.kitchenAssembler.toModel(kitchen);
	}
	
	@PutMapping("/{id}")
	public KitchenModel update(@PathVariable Long id, @RequestBody KitchenIdInput kitchenIdInput) {

		KitchenEntity kitchen = this.kitchenDisassembler.toDomainObject(kitchenIdInput);
		kitchen = cadastroCozinha.save(id, kitchen);

		return this.kitchenAssembler.toModel(kitchen);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		cadastroCozinha.delete(id);
	}
}
