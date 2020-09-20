package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.PaymentModel;
import br.com.luciano.delivery.domain.entity.PaymentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PaymentModel toModel(PaymentEntity paymentEntity) {
        return this.modelMapper.map(paymentEntity, PaymentModel.class);
    }

}
