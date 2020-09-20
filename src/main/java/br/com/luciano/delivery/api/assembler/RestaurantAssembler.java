package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.RestaurantModel;
import br.com.luciano.delivery.domain.entity.RestaurantEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantModel toModel(RestaurantEntity restaurant) {
        return this.modelMapper.map(restaurant, RestaurantModel.class);
    }

}
