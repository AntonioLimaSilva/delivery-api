package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.CityModel;
import br.com.luciano.delivery.domain.entity.CityEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CityModel toModel(CityEntity cityEntity) {
        return this.modelMapper.map(cityEntity, CityModel.class);
    }

}
