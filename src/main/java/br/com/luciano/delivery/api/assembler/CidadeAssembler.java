package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.CidadeModel;
import br.com.luciano.delivery.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeModel toModel(Cidade cidade) {
        return this.modelMapper.map(cidade, CidadeModel.class);
    }

}
