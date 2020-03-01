package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.FormaPagamentoModel;
import br.com.luciano.delivery.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        return this.modelMapper.map(formaPagamento, FormaPagamentoModel.class);
    }

}
