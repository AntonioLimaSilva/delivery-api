package br.com.luciano.delivery.domain.exception;

public class RestaurantNotFoundException extends EntityNotFoundException {

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long restaurantId) {
        this(String.format("Restaurante não encontrado com id: %d", restaurantId));
    }
}
