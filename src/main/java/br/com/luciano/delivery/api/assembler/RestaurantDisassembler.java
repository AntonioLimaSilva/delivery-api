package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.RestaurantInput;
import br.com.luciano.delivery.domain.entity.RestaurantEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantEntity toDomainObject(RestaurantInput restaurantInput) {
        return modelMapper.map(restaurantInput, RestaurantEntity.class);
    }

}
