package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.GroupAssembler;
import br.com.luciano.delivery.api.assembler.GroupDisassembler;
import br.com.luciano.delivery.api.model.GrupoModel;
import br.com.luciano.delivery.api.model.input.GrupoInput;
import br.com.luciano.delivery.domain.model.Grupo;
import br.com.luciano.delivery.domain.service.GroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Grupos")
@RestController
@RequestMapping("/grupos")
public class GroupController {

    @Autowired
    private GroupDisassembler groupDisassembler;

    @Autowired
    private GroupAssembler groupAssembler;

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<GrupoModel> create(@RequestBody @Valid GrupoInput grupoInput) {

        Grupo grupo = this.groupDisassembler.toDomainObject(grupoInput);
        grupo = groupService.salvar(grupo);
        GrupoModel grupoModel = this.groupAssembler.toModel(grupo);

        return ResponseEntity.status(HttpStatus.CREATED).body(grupoModel);
    }

    @GetMapping
    public List<GrupoModel> searchAll() {
        return groupService.buscarTodos()
                .stream().map(g -> this.groupAssembler.toModel(g))
                .collect(Collectors.toList());
    }

    @PutMapping("/{idGrupo}")
    public ResponseEntity<GrupoModel> update(@RequestBody @Valid GrupoInput grupoInput, @PathVariable Long idGrupo) {
        Grupo grupo = this.groupDisassembler.toDomainObject(grupoInput);
        grupo = groupService.atualizar(grupo, idGrupo);

        GrupoModel grupoModel = this.groupAssembler.toModel(grupo);

        return ResponseEntity.status(HttpStatus.CREATED).body(grupoModel);
    }

    @DeleteMapping("/{idGrupo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long idGrupo) {
        groupService.excluir(idGrupo);
    }

}
