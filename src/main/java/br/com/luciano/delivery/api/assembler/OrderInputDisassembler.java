package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.input.OrderInput;
import br.com.luciano.delivery.domain.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public OrderEntity toDomainObject(OrderInput orderInput) {
        return modelMapper.map(orderInput, OrderEntity.class);
    }

    public void copyToDomainObject(OrderInput orderInput, OrderEntity orderEntity) {
        modelMapper.map(orderInput, orderEntity);
    }

}
