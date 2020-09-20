package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.StateModel;
import br.com.luciano.delivery.domain.entity.StateEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public StateModel toModel(StateEntity stateEntity) {
        return this.modelMapper.map(stateEntity, StateModel.class);
    }

}
