package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.RestaurantAssembler;
import br.com.luciano.delivery.api.assembler.RestaurantDisassembler;
import br.com.luciano.delivery.api.model.RestaurantModel;
import br.com.luciano.delivery.api.model.input.RestauranteInput;
import br.com.luciano.delivery.domain.entity.RestaurantEntity;
import br.com.luciano.delivery.domain.service.RestaurantService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Restaurants")
@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private RestaurantAssembler restaurantAssembler;

	@Autowired
	private RestaurantDisassembler restaurantDisassembler;
	
	@GetMapping
	public List<RestaurantModel> searchAll() {
		return restaurantService.findAll().stream().map(r -> this.restaurantAssembler.toModel(r))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public RestaurantModel searchBy(@PathVariable Long id) {
		return this.restaurantAssembler.toModel(restaurantService.findByIdOrFail(id));
	}
	
	@PostMapping
	public ResponseEntity<RestaurantModel> create(@RequestBody @Valid RestauranteInput restauranteInput) {

		RestaurantEntity restaurant = this.restaurantService.save(this.restaurantDisassembler.toDomainObject(restauranteInput));

		return ResponseEntity.status(HttpStatus.CREATED).body(restaurantAssembler.toModel(restaurant));
	}

	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activate(@PathVariable(name = "id") Long id) {
		this.restaurantService.activate(id);
	}

	@DeleteMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inactivate(@PathVariable(name = "id") Long id) {
		this.restaurantService.inactivate(id);
	}
	
	@PutMapping("/{id}")
	public RestaurantModel update(@PathVariable Long id, @Valid @RequestBody RestauranteInput restaurant) {
		return this.restaurantAssembler.toModel(restaurantService.save(id, this.restaurantDisassembler.toDomainObject(restaurant)));
	}
}
