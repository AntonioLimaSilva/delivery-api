package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.entity.*;
import br.com.luciano.delivery.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CityService cityService;
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final PaymentService paymentService;

    @Transactional
    public OrderEntity emit(OrderEntity newOrder) {
        newOrder.setTotal(new BigDecimal(100));
        newOrder.setSubtotal(new BigDecimal(90));
        newOrder.setShippingFee(new BigDecimal(10));
        newOrder.setStatus(StatusOrder.CREATED);

        this.validateOrder(newOrder);
        this.validateItems(newOrder);

        return orderRepository.save(newOrder);
    }

    private void validateOrder(OrderEntity newOrder) {
        CityEntity cityEntity = cityService.findByIdOrFail(newOrder.getAddress().getCity().getId());
        UserEntity client = userService.findOrFail(newOrder.getUser().getId());
        RestaurantEntity restaurantEntity = restaurantService.findOrFail(newOrder.getRestaurant().getId());
        PaymentEntity paymentEntity = paymentService.findByIdOrFail(newOrder.getPayment().getId());

        newOrder.getAddress().setCity(cityEntity);
        newOrder.setUser(client);
        newOrder.setRestaurant(restaurantEntity);
        newOrder.setPayment(paymentEntity);
    }

    private void validateItems(OrderEntity newOrder) {
        newOrder.getItems().forEach(item -> {
            ProductEntity productEntity = productService.findOrFail(item.getProduct().getId());

            item.setOrder(newOrder);
            item.setProduct(productEntity);
            item.setUnitPrice(productEntity.getPrice());
            item.setTotalPrice(new BigDecimal(100));
        });
    }
}
