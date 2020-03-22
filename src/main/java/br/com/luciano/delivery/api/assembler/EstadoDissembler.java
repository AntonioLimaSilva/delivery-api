package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.EstadoInput;
import br.com.luciano.delivery.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoDissembler {

    @Autowired
    private ModelMapper modelMapper;

    public Estado toDomainObject(EstadoInput estadoInput) {
        return this.modelMapper.map(estadoInput, Estado.class);
    }

}
