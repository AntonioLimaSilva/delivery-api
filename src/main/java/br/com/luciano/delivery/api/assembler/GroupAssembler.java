package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.GrupoModel;
import br.com.luciano.delivery.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoModel toModel(Grupo grupo) {
        return this.modelMapper.map(grupo, GrupoModel.class);
    }

}
