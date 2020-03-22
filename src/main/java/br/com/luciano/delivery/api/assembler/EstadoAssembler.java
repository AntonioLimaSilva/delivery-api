package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.EstadoModel;
import br.com.luciano.delivery.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoModel toModel(Estado estado) {
        return this.modelMapper.map(estado, EstadoModel.class);
    }

}
