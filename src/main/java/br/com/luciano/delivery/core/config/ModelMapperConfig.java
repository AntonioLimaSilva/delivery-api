package br.com.luciano.delivery.core.config;

import br.com.luciano.delivery.api.model.EnderecoModel;
import br.com.luciano.delivery.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Endereco, EnderecoModel> typeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

        typeMap.<String>addMapping(src -> src.getCidade().getEstado().getNome(),
                (dest, value) -> dest.getCidade().setEstado(value));

        return modelMapper;
    }

}
