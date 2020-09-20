package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.PaymentDescriptionInput;
import br.com.luciano.delivery.domain.entity.PaymentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public PaymentEntity toDomainObject(PaymentDescriptionInput paymentDescriptionInput) {
        return this.modelMapper.map(paymentDescriptionInput, PaymentEntity.class);
    }

}
