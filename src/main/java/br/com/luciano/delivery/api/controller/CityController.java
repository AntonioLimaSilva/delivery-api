package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.CityAssembler;
import br.com.luciano.delivery.api.assembler.CityDisassembler;
import br.com.luciano.delivery.api.model.CityModel;
import br.com.luciano.delivery.api.model.input.CityInput;
import br.com.luciano.delivery.domain.entity.CityEntity;
import br.com.luciano.delivery.domain.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Cities")
@RestController
@RequestMapping(value = "/cities")
public class CityController {

	private static final Logger log = LoggerFactory.getLogger(CityController.class);
	
	@Autowired
	private CityService cityService;

	@Autowired
	private CityAssembler cityAssembler;

	@Autowired
	private CityDisassembler cityDisassembler;

	@ApiOperation("Lista as cidades")
	@GetMapping
	public List<CityModel> searchAll() {
		log.debug("Início do processamento controller cidades");

		return cityService.findAll().stream()
				.map(c -> this.cityAssembler.toModel(c))
				.collect(Collectors.toList());
	}

	@ApiOperation("find by id")
	@GetMapping("/{id}")
	public CityModel searchBy(@ApiParam(value = "city id", example = "1")
							  @PathVariable Long id) {
		return this.cityAssembler.toModel(cityService.findByIdOrFail(id));
	}

	@ApiOperation("Create new city")
	@PostMapping
	public ResponseEntity<CityModel> create(@ApiParam(name = "Body city", value = "Representation of city")
												 @RequestBody @Valid CityInput cityInput) {
		CityEntity cityEntity = this.cityDisassembler.toDomainObject(cityInput);
		CityModel cityModel = this.cityAssembler.toModel(cityService.save(cityEntity));

		return ResponseEntity.status(HttpStatus.CREATED).body(cityModel);
	}

	@ApiOperation("Update city existing")
	@PutMapping("/{id}")
	public CityModel update(@ApiParam(value = "Id of city", example = "1")
								 @PathVariable Long id,
							@ApiParam(name = "Body city", value = "Representation of city")
								 @RequestBody CityInput cityInput) {
		CityEntity cityEntity = this.cityDisassembler.toDomainObject(cityInput);
		return this.cityAssembler.toModel(this.cityService.save(id, cityEntity));
	}

	@ApiOperation("Remove city by id")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@ApiParam(value = "city id", example = "1") @PathVariable Long id) {
		cityService.delete(id);
	}
	
}
