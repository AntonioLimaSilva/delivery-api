package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.GroupNameInput;
import br.com.luciano.delivery.domain.entity.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Group toDomainObject(GroupNameInput groupNameInput) {
        return modelMapper.map(groupNameInput, Group.class);
    }

}
