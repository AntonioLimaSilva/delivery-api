package br.com.luciano.delivery.api.controller;

import br.com.luciano.delivery.api.assembler.OrderAssembler;
import br.com.luciano.delivery.api.assembler.OrderInputDisassembler;
import br.com.luciano.delivery.api.model.OrderModel;
import br.com.luciano.delivery.api.model.input.OrderInput;
import br.com.luciano.delivery.domain.entity.OrderEntity;
import br.com.luciano.delivery.domain.entity.UserEntity;
import br.com.luciano.delivery.domain.exception.EntityNotFoundException;
import br.com.luciano.delivery.domain.exception.BusinessException;
import br.com.luciano.delivery.domain.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Orders")
@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderInputDisassembler orderInputDisassembler;

    @Autowired
    private OrderAssembler orderAssembler;

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderModel create(@Valid @RequestBody OrderInput orderInput) {
        try {
            OrderEntity newOrder = orderInputDisassembler.toDomainObject(orderInput);

            newOrder.setUser(new UserEntity());
            newOrder.getUser().setId(1L);

            newOrder = orderService.emit(newOrder);

            return orderAssembler.toModel(newOrder);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

}
