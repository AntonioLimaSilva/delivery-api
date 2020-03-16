package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.GrupoAssembler;
import br.com.luciano.delivery.api.assembler.GrupoDisassembler;
import br.com.luciano.delivery.api.model.GrupoModel;
import br.com.luciano.delivery.api.model.input.GrupoInput;
import br.com.luciano.delivery.domain.model.Grupo;
import br.com.luciano.delivery.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoDisassembler grupoDisassembler;

    @Autowired
    private GrupoAssembler grupoAssembler;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @PostMapping
    public ResponseEntity<GrupoModel> adicionar(@RequestBody @Valid GrupoInput grupoInput) {

        Grupo grupo = this.grupoDisassembler.toDomainObject(grupoInput);
        grupo = cadastroGrupoService.salvar(grupo);
        GrupoModel grupoModel = this.grupoAssembler.toModel(grupo);

        return ResponseEntity.status(HttpStatus.CREATED).body(grupoModel);
    }

    @GetMapping
    public List<GrupoModel> buscarTodos() {
        return cadastroGrupoService.buscarTodos()
                .stream().map(g -> this.grupoAssembler.toModel(g))
                .collect(Collectors.toList());
    }

    @PutMapping("/{idGrupo}")
    public ResponseEntity<GrupoModel> editar(@RequestBody @Valid GrupoInput grupoInput, @PathVariable Long idGrupo) {
        Grupo grupo = this.grupoDisassembler.toDomainObject(grupoInput);
        grupo = cadastroGrupoService.atualizar(grupo, idGrupo);

        GrupoModel grupoModel = this.grupoAssembler.toModel(grupo);

        return ResponseEntity.status(HttpStatus.CREATED).body(grupoModel);
    }

    @DeleteMapping("/{idGrupo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long idGrupo) {
        cadastroGrupoService.excluir(idGrupo);
    }

}
