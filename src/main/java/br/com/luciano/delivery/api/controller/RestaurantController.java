package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.RestaurantAssembler;
import br.com.luciano.delivery.api.assembler.RestaurantDisassembler;
import br.com.luciano.delivery.api.model.RestauranteModel;
import br.com.luciano.delivery.api.model.input.RestauranteInput;
import br.com.luciano.delivery.domain.model.Restaurante;
import br.com.luciano.delivery.domain.service.RestaurantService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Restaurantes")
@RestController
@RequestMapping(value = "/restaurantes")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private RestaurantAssembler restaurantAssembler;

	@Autowired
	private RestaurantDisassembler restaurantDisassembler;
	
	@GetMapping
	public List<RestauranteModel> searchAll() {
		return restaurantService.buscarTodos().stream().map(r -> this.restaurantAssembler.toModel(r))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{restauranteId}")
	public RestauranteModel searchBy(@PathVariable Long restauranteId) {
		return this.restaurantAssembler.toModel(restaurantService.buscarPorId(restauranteId));
	}
	
	@PostMapping
	public ResponseEntity<RestauranteModel> create(@RequestBody @Valid RestauranteInput restauranteInput) {

		Restaurante restaurante = this.restaurantService.salvar(this.restaurantDisassembler.toDomainObject(restauranteInput));

		return ResponseEntity.status(HttpStatus.CREATED).body(restaurantAssembler.toModel(restaurante));
	}

	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activate(@PathVariable(name = "restauranteId") Long id) {
		this.restaurantService.ativar(id);
	}

	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inactivate(@PathVariable(name = "restauranteId") Long id) {
		this.restaurantService.inativar(id);
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteModel update(@PathVariable Long restauranteId, @Valid @RequestBody RestauranteInput restaurante) {
		return this.restaurantAssembler.toModel(restaurantService.salvar(restauranteId, this.restaurantDisassembler.toDomainObject(restaurante)));
	}
	
}
