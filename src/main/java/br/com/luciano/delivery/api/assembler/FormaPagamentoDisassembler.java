package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.FormaPagamentoInput;
import br.com.luciano.delivery.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
        return this.modelMapper.map(formaPagamentoInput, FormaPagamento.class);
    }

}
