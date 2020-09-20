package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.RestauranteModel;
import br.com.luciano.delivery.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteModel toModel(Restaurante restaurante) {
        return this.modelMapper.map(restaurante, RestauranteModel.class);
    }

}
