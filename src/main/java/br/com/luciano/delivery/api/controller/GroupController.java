package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.GroupAssembler;
import br.com.luciano.delivery.api.assembler.GroupDisassembler;
import br.com.luciano.delivery.api.model.GroupModel;
import br.com.luciano.delivery.api.model.input.GroupNameInput;
import br.com.luciano.delivery.domain.entity.Group;
import br.com.luciano.delivery.domain.service.GroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Groups")
@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupDisassembler groupDisassembler;

    @Autowired
    private GroupAssembler groupAssembler;

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupModel> create(@RequestBody @Valid GroupNameInput groupNameInput) {

        Group group = this.groupDisassembler.toDomainObject(groupNameInput);
        group = groupService.save(group);
        GroupModel groupModel = this.groupAssembler.toModel(group);

        return ResponseEntity.status(HttpStatus.CREATED).body(groupModel);
    }

    @GetMapping
    public List<GroupModel> searchAll() {
        return groupService.findAll()
                .stream().map(g -> this.groupAssembler.toModel(g))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupModel> update(@RequestBody @Valid GroupNameInput groupNameInput, @PathVariable Long id) {
        Group group = this.groupDisassembler.toDomainObject(groupNameInput);
        group = groupService.update(group, id);

        GroupModel groupModel = this.groupAssembler.toModel(group);

        return ResponseEntity.status(HttpStatus.CREATED).body(groupModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        groupService.delete(id);
    }

}
