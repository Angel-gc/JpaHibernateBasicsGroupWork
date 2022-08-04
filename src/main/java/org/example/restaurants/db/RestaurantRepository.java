package org.example.restaurants.db;


import org.example.restaurants.model.Restaurant;
import org.example.restaurants.service.RepositoryEnhanced;
import org.example.shared.io.db.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RestaurantRepository implements Repository<Restaurant> {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    @Override
    public Restaurant save(Restaurant restaurant) {
        transaction.begin();
        entityManager.persist(restaurant);
        transaction.commit();
        return restaurant;
    }

    @Transactional
    @Override
    public Optional<Restaurant> findById(Long id) {
        Restaurant res = find("ID", String.valueOf(id));
        return Optional.ofNullable(res);
    }

    @Transactional
    public Restaurant find(String column, String value){
        String query = "select R from Restaurant R where " + column + " = '" + value + "'";
        return entityManager.createQuery(query, Restaurant.class).getResultList().get(0);
    }
    @Transactional
    public void printRestaurants() {
        List<Restaurant> restaurantResult = entityManager.createQuery("SELECT r FROM Restaurant r ORDER BY Review DESC",
                Restaurant.class
        ).getResultList();

        System.out.println(
                restaurantResult.stream().map(Restaurant::toString).collect(Collectors.joining("\n")));
    }

}
