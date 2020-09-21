package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.PermissionAssembler;
import br.com.luciano.delivery.api.assembler.PermissionDisassembler;
import br.com.luciano.delivery.api.model.PermissionModel;
import br.com.luciano.delivery.api.model.input.GroupNameInput;
import br.com.luciano.delivery.domain.entity.PermissionEntity;
import br.com.luciano.delivery.domain.service.PermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Permissions")
@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionDisassembler permissionDisassembler;

    @Autowired
    private PermissionAssembler permissionAssembler;

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ResponseEntity<PermissionModel> create(@RequestBody @Valid GroupNameInput groupNameInput) {

        PermissionEntity permissionEntity = this.permissionDisassembler.toDomainObject(groupNameInput);
        permissionEntity = permissionService.save(permissionEntity);
        PermissionModel permissionModel = this.permissionAssembler.toModel(permissionEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(permissionModel);
    }

    @GetMapping
    public List<PermissionModel> searchAll() {
        return permissionService.findAll()
                .stream().map(g -> this.permissionAssembler.toModel(g))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionModel> update(@RequestBody @Valid GroupNameInput groupNameInput, @PathVariable Long id) {
        PermissionEntity permissionEntity = this.permissionDisassembler.toDomainObject(groupNameInput);
        permissionEntity = permissionService.update(permissionEntity, id);

        PermissionModel permissionModel = this.permissionAssembler.toModel(permissionEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(permissionModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        permissionService.delete(id);
    }

}
