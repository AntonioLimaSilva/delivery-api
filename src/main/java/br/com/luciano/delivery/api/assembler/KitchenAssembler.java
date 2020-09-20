package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.KitchenModel;
import br.com.luciano.delivery.domain.entity.KitchenEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KitchenAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public KitchenModel toModel(KitchenEntity kitchen) {
        return modelMapper.map(kitchen, KitchenModel.class);
    }

}
