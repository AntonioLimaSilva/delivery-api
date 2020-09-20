package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.StateNameInput;
import br.com.luciano.delivery.domain.entity.StateEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateDissembler {

    @Autowired
    private ModelMapper modelMapper;

    public StateEntity toDomainObject(StateNameInput stateNameInput) {
        return this.modelMapper.map(stateNameInput, StateEntity.class);
    }

}
