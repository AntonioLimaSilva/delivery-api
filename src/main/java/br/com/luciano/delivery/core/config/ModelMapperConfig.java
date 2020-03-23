package br.com.luciano.delivery.core.config;

import br.com.luciano.delivery.api.model.EnderecoModel;
import br.com.luciano.delivery.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class) // integração com o bean validation do springfox, para adicionar required
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
