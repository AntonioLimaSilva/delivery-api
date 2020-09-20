package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.CityAssembler;
import br.com.luciano.delivery.api.assembler.CityDisassembler;
import br.com.luciano.delivery.api.model.CidadeModel;
import br.com.luciano.delivery.api.model.input.CidadeInput;
import br.com.luciano.delivery.domain.model.Cidade;
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

@Api(tags = "Cidades")
@RestController
@RequestMapping(value = "/cidades")
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
	public List<CidadeModel> searchAll() {
		log.debug("Início do processamento controller cidades");

		return cityService.buscarTodas().stream()
				.map(c -> this.cityAssembler.toModel(c))
				.collect(Collectors.toList());
	}

	@ApiOperation("Buscar cidade por id")
	@GetMapping("/{cidadeId}")
	public CidadeModel searchBy(@ApiParam(value = "ID de uma cidade", example = "1")
							  @PathVariable Long cidadeId) {
		return this.cityAssembler.toModel(cityService.buscarPorId(cidadeId));
	}

	@ApiOperation("Adiciona uma cidade")
	@PostMapping
	public ResponseEntity<CidadeModel> create(@ApiParam(name = "Corpo", value = "Representação de uma cidade")
												 @RequestBody @Valid CidadeInput cidadeInput) {
		Cidade cidade = this.cityDisassembler.toDomainObject(cidadeInput);
		CidadeModel cidadeModel = this.cityAssembler.toModel(cityService.salvar(cidade));

		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeModel);
	}

	@ApiOperation("Altera uma cidade")
	@PutMapping("/{cidadeId}")
	public CidadeModel update(@ApiParam(value = "ID de uma cidade", example = "1")
								 @PathVariable Long cidadeId,
							  @ApiParam(name = "Corpo", value = "Representação de uma cidade")
								 @RequestBody CidadeInput cidadeInput) {
		Cidade cidade = this.cityDisassembler.toDomainObject(cidadeInput);
		return this.cityAssembler.toModel(this.cityService.salvar(cidadeId, cidade));
	}

	@ApiOperation("Remove uma cidade por id")
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long cidadeId) {
		cityService.excluir(cidadeId);
	}
	
}
