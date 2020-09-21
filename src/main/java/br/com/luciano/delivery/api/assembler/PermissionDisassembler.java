package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.GroupNameInput;
import br.com.luciano.delivery.domain.entity.PermissionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissionEntity toDomainObject(GroupNameInput groupNameInput) {
        return modelMapper.map(groupNameInput, PermissionEntity.class);
    }

}
