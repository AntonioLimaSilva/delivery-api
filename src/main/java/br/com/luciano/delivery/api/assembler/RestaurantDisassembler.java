package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.RestauranteInput;
import br.com.luciano.delivery.domain.entity.RestaurantEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantEntity toDomainObject(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, RestaurantEntity.class);
    }

}
