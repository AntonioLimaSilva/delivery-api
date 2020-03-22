package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.RestauranteAssembler;
import br.com.luciano.delivery.api.assembler.RestauranteDisassembler;
import br.com.luciano.delivery.api.model.RestauranteModel;
import br.com.luciano.delivery.api.model.input.RestauranteInput;
import br.com.luciano.delivery.domain.exception.ValidationRestauranteException;
import br.com.luciano.delivery.domain.model.Restaurante;
import br.com.luciano.delivery.domain.repository.RestauranteRepository;
import br.com.luciano.delivery.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "Restaurantes")
@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private SmartValidator smartValidator;

	@Autowired
	private RestauranteAssembler restauranteAssembler;

	@Autowired
	private RestauranteDisassembler restauranteDisassembler;
	
	@GetMapping
	public List<RestauranteModel> listar() {
		return cadastroRestaurante.buscarTodos().stream().map(r -> this.restauranteAssembler.toModel(r))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId) {
		return this.restauranteAssembler.toModel(cadastroRestaurante.buscarPorId(restauranteId));
	}
	
	@PostMapping
	public ResponseEntity<RestauranteModel> adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {

		Restaurante restaurante = this.cadastroRestaurante.salvar(this.restauranteDisassembler.toDomainObject(restauranteInput));

		return ResponseEntity.status(HttpStatus.CREATED).body(restauranteAssembler.toModel(restaurante));
	}

	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable(name = "restauranteId") Long id) {
		this.cadastroRestaurante.ativar(id);
	}

	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable(name = "restauranteId") Long id) {
		this.cadastroRestaurante.inativar(id);
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Long restauranteId, @Valid @RequestBody RestauranteInput restaurante) {
		return this.restauranteAssembler.toModel(cadastroRestaurante.salvar(restauranteId, this.restauranteDisassembler.toDomainObject(restaurante)));
	}
	
//	@PatchMapping("/{restauranteId}")
//	public RestauranteModel atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
//		Restaurante restauranteAtual = cadastroRestaurante.buscarPorId(restauranteId);
//
//		this.restauranteDisassembler.toDomainObject()
//
//		this.restauranteAssembler.toModel(restauranteAtual);
//
//		merge(campos, restauranteAtual);
//		validate(restauranteAtual, "restaurante");
//
//		return atualizar(restauranteId, );
//	}

	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		this.smartValidator.validate(restaurante, bindingResult);

		if (bindingResult.hasErrors()) {
			throw new ValidationRestauranteException(bindingResult);
		}
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true)
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

		try {
			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				assert field != null;
				field.setAccessible(true);

				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		} catch (IllegalArgumentException ex) {
			Throwable rootCause = ExceptionUtils.getRootCause(ex);
			throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, null);
		}
	}
	
}
