package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.entity.CityEntity;
import br.com.luciano.delivery.domain.entity.KitchenEntity;
import br.com.luciano.delivery.domain.entity.RestaurantEntity;
import br.com.luciano.delivery.domain.exception.*;
import br.com.luciano.delivery.domain.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenService kitchenService;

	@Autowired
	private CityService cityService;

	@Transactional
	public RestaurantEntity save(RestaurantEntity restaurant) {
		KitchenEntity kitchen = kitchenService.findByIdOrFail(restaurant.getKitchen().getId());
		CityEntity cityEntity = cityService.findByIdOrFail(restaurant.getAddress().getCity().getId());

		restaurant.setKitchen(kitchen);
		restaurant.getAddress().setCity(cityEntity);
		
		return restaurantRepository.save(restaurant);
	}

    public RestaurantEntity findByIdOrFail(Long id) {
		return this.restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Transactional
	public RestaurantEntity save(Long id, RestaurantEntity restaurant) {
		RestaurantEntity restaurantActual = this.findByIdOrFail(id);

		BeanUtils.copyProperties(restaurant, restaurantActual, "id", "payments", "createAt", "products");

		return this.save(restaurantActual);
	}

	@Transactional
	public void activate(Long id) {
		RestaurantEntity restaurant = this.findByIdOrFail(id);
		restaurant.setActive(true);
	}

	@Transactional
	public void inactivate(Long id) {
		RestaurantEntity restaurant = this.findByIdOrFail(id);
		restaurant.setActive(false);
	}

	public RestaurantEntity findOrFail(Long restaurantId) {
		return this.restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
	}

	public List<RestaurantEntity> findAll() {
		return this.restaurantRepository.findAll();
	}
}
