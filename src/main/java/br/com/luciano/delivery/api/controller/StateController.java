package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.StateAssembler;
import br.com.luciano.delivery.api.assembler.StateDissembler;
import br.com.luciano.delivery.api.model.StateModel;
import br.com.luciano.delivery.api.model.input.StateNameInput;
import br.com.luciano.delivery.domain.entity.StateEntity;
import br.com.luciano.delivery.domain.service.StateService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "States")
@RestController
@RequestMapping("/states")
public class StateController {
	
	@Autowired
	private StateService stateService;

	@Autowired
	private StateAssembler stateAssembler;

	@Autowired
	private StateDissembler stateDissembler;
	
	@GetMapping
	public List<StateModel> searchAll() {
		return stateService.findAll().stream()
				.map(e -> this.stateAssembler.toModel(e))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public StateModel searchBy(@PathVariable Long id) {
		return this.stateAssembler.toModel(stateService.findByIdOrFail(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StateModel create(@RequestBody @Valid StateNameInput stateNameInput) {
		StateEntity stateEntity = this.stateDissembler.toDomainObject(stateNameInput);
		stateEntity = stateService.save(stateEntity);

		return this.stateAssembler.toModel(stateEntity);
	}
	
	@PutMapping("/{id}")
	public StateModel update(@PathVariable Long id, @RequestBody StateNameInput stateNameInput) {
		StateEntity stateEntity = this.stateDissembler.toDomainObject(stateNameInput);
		stateEntity = this.stateService.save(id, stateEntity);

		return this.stateAssembler.toModel(stateEntity);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		this.stateService.delete(id);
	}
	
}
