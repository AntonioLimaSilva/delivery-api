package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.CidadeAssembler;
import br.com.luciano.delivery.api.assembler.CidadeDisassembler;
import br.com.luciano.delivery.api.model.CidadeModel;
import br.com.luciano.delivery.api.model.input.CidadeInput;
import br.com.luciano.delivery.domain.model.Cidade;
import br.com.luciano.delivery.domain.service.CadastroCidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
public class CidadeController {
	
	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeAssembler cidadeAssembler;

	@Autowired
	private CidadeDisassembler cidadeDisassembler;

	@ApiOperation("Lista as cidades")
	@GetMapping
	public List<CidadeModel> listar() {
		return cadastroCidade.buscarTodas().stream()
				.map(c -> this.cidadeAssembler.toModel(c))
				.collect(Collectors.toList());
	}

	@ApiOperation("Buscar cidade por id")
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@ApiParam(value = "ID de uma cidade", example = "1")
							  @PathVariable Long cidadeId) {
		return this.cidadeAssembler.toModel(cadastroCidade.buscarPorId(cidadeId));
	}

	@ApiOperation("Adiciona uma cidade")
	@PostMapping
	public ResponseEntity<CidadeModel> adicionar(@ApiParam(name = "Corpo", value = "Representação de uma cidade")
												 @RequestBody @Valid CidadeInput cidadeInput) {
		Cidade cidade = this.cidadeDisassembler.toDomainObject(cidadeInput);
		CidadeModel cidadeModel = this.cidadeAssembler.toModel(cadastroCidade.salvar(cidade));

		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeModel);
	}

	@ApiOperation("Altera uma cidade")
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@ApiParam(value = "ID de uma cidade", example = "1")
								 @PathVariable Long cidadeId,
								 @ApiParam(name = "Corpo", value = "Representação de uma cidade")
								 @RequestBody CidadeInput cidadeInput) {
		Cidade cidade = this.cidadeDisassembler.toDomainObject(cidadeInput);
		return this.cidadeAssembler.toModel(this.cadastroCidade.salvar(cidadeId, cidade));
	}

	@ApiOperation("Remove uma cidade por id")
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
	
}
