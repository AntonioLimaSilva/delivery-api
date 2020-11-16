package br.com.luciano.delivery.core.config;

import br.com.luciano.delivery.api.model.AddressModel;
import br.com.luciano.delivery.api.model.input.ItemInput;
import br.com.luciano.delivery.domain.entity.Address;
import br.com.luciano.delivery.domain.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Address, AddressModel> typeMap = modelMapper.createTypeMap(Address.class, AddressModel.class);

        typeMap.<String>addMapping(src -> src.getCity().getState().getName(),
                (dest, value) -> dest.getCity().setState(value));

        modelMapper.createTypeMap(ItemInput.class, ItemEntity.class)
                .addMappings(mapper -> mapper.skip(ItemEntity::setId));

        return modelMapper;
    }
}
