package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.GroupModel;
import br.com.luciano.delivery.domain.entity.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GroupModel toModel(Group group) {
        return this.modelMapper.map(group, GroupModel.class);
    }

}
