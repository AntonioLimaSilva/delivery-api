package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.CityInput;
import br.com.luciano.delivery.domain.entity.CityEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public CityEntity toDomainObject(CityInput cityInput) {
        return this.modelMapper.map(cityInput, CityEntity.class);
    }

}
