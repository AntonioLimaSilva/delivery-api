package br.com.luciano.delivery.core.config;

import br.com.luciano.delivery.api.model.EnderecoModel;
import br.com.luciano.delivery.domain.entity.Address;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Address, EnderecoModel> typeMap = modelMapper.createTypeMap(Address.class, EnderecoModel.class);

        typeMap.<String>addMapping(src -> src.getCity().getState().getName(),
                (dest, value) -> dest.getCidade().setCountry(value));

        return modelMapper;
    }
}
