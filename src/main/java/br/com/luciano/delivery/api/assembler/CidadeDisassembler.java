package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.CidadeInput;
import br.com.luciano.delivery.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInput cidadeInput) {
        return this.modelMapper.map(cidadeInput, Cidade.class);
    }

}
