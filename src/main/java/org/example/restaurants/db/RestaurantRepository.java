package org.example.restaurants.db;


import org.example.restaurants.model.Dish;
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
        try {
            return entityManager.createQuery(query, Restaurant.class).getResultList().get(0);
        }catch (Exception e){
            return null;
        }
    }
    @Transactional
    public void printRestaurants() {
        List<Restaurant> restaurantResult = entityManager.createQuery("SELECT r FROM Restaurant r ORDER BY Review DESC",
                Restaurant.class
        ).getResultList();

        System.out.println(
                restaurantResult.stream().map(Restaurant::toString).collect(Collectors.joining("\n")));
    }

    public void setProps() {

        transaction.begin();
        Restaurant res1 = new Restaurant();
        Restaurant res2 = new Restaurant();
        Restaurant res3 = new Restaurant();

        res1.setName("Olive Garden");
        res1.setLocation("Times SQ");
        res1.setReview(3);

        res2.setName("Red lobster");
        res2.setLocation("Bay Plaza");
        res2.setReview(4);

        res3.setName("Hale and Hearty");
        res3.setLocation("Midtown");
        res3.setReview(5);
        if (find("NAME", res1.getName()) == null){
            Dish dish = new Dish("Fried Calamari", 50, "this is chewy but GOOD", 5, res2);
            res1.setDishes(List.of(dish));
            dish.setRestaurant(res1);
            entityManager.persist(res1);
            entityManager.persist(dish);
        }
        if (find("NAME", res2.getName()) == null){
            Dish dish = new Dish("Fried Calamari", 50, "this is chewy but GOOD", 5, res2);
            res2.setDishes(List.of(dish));
            dish.setRestaurant(res2);
         entityManager.persist(res2);
         entityManager.persist(dish);
        }
        if (find("NAME", res3.getName()) == null){
            Dish dish = new Dish("Durian soup", 100, "tastes like vomit", 0,  res3);
            res3.setDishes(List.of(dish));
            dish.setRestaurant(res3);
           entityManager.persist(res3);
           entityManager.persist(dish);
        }
        transaction.commit();
    }

}
