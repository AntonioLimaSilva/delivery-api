package br.com.luciano.delivery.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.luciano.delivery.domain.entity.RestaurantEntity;
import br.com.luciano.delivery.infrastructure.repository.spec.RestaurantSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.luciano.delivery.domain.repository.RestaurantRepository;
import br.com.luciano.delivery.domain.repository.RestaurantRepositoryQueries;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestaurantRepository restaurantRepository;
	
	@Override
	public List<RestaurantEntity> find(String nome, BigDecimal initialShippingFee, BigDecimal finalShippingFee) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery criteria = builder.createQuery(RestaurantEntity.class);
		Root root = criteria.from(RestaurantEntity.class);

		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("name"), "%" + nome + "%"));
		}

		if (initialShippingFee != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("shippingFee"), initialShippingFee));
		}

		if (finalShippingFee != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("shippingFee"), finalShippingFee));
		}

		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery query = manager.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<RestaurantEntity> findWithFreeShipping(String name) {
		return restaurantRepository.findAll(RestaurantSpecs.withShippingFee()
				.and(RestaurantSpecs.withSimilarName(name)));
	}
	
}
