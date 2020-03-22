package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.CidadeAssembler;
import br.com.luciano.delivery.api.assembler.CidadeDisassembler;
import br.com.luciano.delivery.api.model.CidadeModel;
import br.com.luciano.delivery.api.model.input.CidadeInput;
import br.com.luciano.delivery.domain.model.Cidade;
import br.com.luciano.delivery.domain.service.CadastroCidadeService;
import io.swagger.annotations.Api;
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
	
	@GetMapping
	public List<CidadeModel> listar() {
		return cadastroCidade.buscarTodas().stream()
				.map(c -> this.cidadeAssembler.toModel(c))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		return this.cidadeAssembler.toModel(cadastroCidade.buscarPorId(cidadeId));
	}
	
	@PostMapping
	public ResponseEntity<CidadeModel> adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		Cidade cidade = this.cidadeDisassembler.toDomainObject(cidadeInput);
		CidadeModel cidadeModel = this.cidadeAssembler.toModel(cadastroCidade.salvar(cidade));

		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeModel);
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody CidadeInput cidadeInput) {
		Cidade cidade = this.cidadeDisassembler.toDomainObject(cidadeInput);
		return this.cidadeAssembler.toModel(this.cadastroCidade.salvar(cidadeId, cidade));
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
	
}
