package br.com.luciano.delivery.api.assembler;

import br.com.luciano.delivery.api.model.OrderModel;
import br.com.luciano.delivery.domain.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public OrderModel toModel(OrderEntity orderEntity) {
        return this.modelMapper.map(orderEntity, OrderModel.class);
    }

}
