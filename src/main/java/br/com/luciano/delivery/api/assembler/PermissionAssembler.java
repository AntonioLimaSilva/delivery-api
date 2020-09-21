package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.PermissionModel;
import br.com.luciano.delivery.domain.entity.PermissionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissionModel toModel(PermissionEntity permission) {
        return this.modelMapper.map(permission, PermissionModel.class);
    }

}
