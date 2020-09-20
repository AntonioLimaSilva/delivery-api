package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.KitchenIdInput;
import br.com.luciano.delivery.domain.entity.KitchenEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KitchenDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public KitchenEntity toDomainObject(KitchenIdInput kitchenIdInput) {
        return modelMapper.map(kitchenIdInput, KitchenEntity.class);
    }

}
