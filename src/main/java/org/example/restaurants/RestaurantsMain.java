package org.example.restaurants;

import org.example.jaystuff.model.PrimaryTeacher;
import org.example.jaystuff.model.Student;
import org.example.restaurants.model.Restaurant;
import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.validation.NonBlankInputValidationRule;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantsMain {
    public static void main(String[] args) throws Exception {
        UserOutputService userOutputService = new ConsoleUserOutputServiceImpl();
        try (UserInputService userInputService = new ConsoleUserInputServiceImpl(userOutputService)) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            // access transaction object (copy paste this when needed)
            EntityTransaction transaction = entityManager.getTransaction();
            // create and use transactions (copy paste this when needed)
            while(true) {
                transaction.begin();
                userOutputService.print("WELCOME");
                final String printOption = "Print all Restaurants";
                final String createOption = "Create a new Restaurant";

                String response = userInputService.getUserChoice("Please choose an option.", printOption, createOption);

                switch (response) {
                    case printOption -> printRestaurants(entityManager);
                    case createOption -> {
                        String restaurantName = userInputService.getUserInput("What is the restaurants name?");
                        String restaurantLocation = userInputService.getUserInput("What is the restaurants location?");
                        int restaurantReview = Integer.parseInt(userInputService.getUserInput("What is the restaurants review? (0-5)"));
                        Restaurant restaurantToAdd = new Restaurant(restaurantName, restaurantLocation, restaurantReview);
                        createRestaurant(restaurantToAdd, entityManager);
                    }
                    default -> System.out.println("");
                }
                // IMPORTANT!
                transaction.commit();
                final String endOption = "End Program";
                final String continueOption = "Continue Program";
                response = userInputService.getUserChoice("Do you want to end the program?", endOption, continueOption);
                if (response.equals(endOption)){
                    return;
                }
            }
        }
    }
    public static void printRestaurants(EntityManager em) {
        List<Restaurant> restaurantResult = em.createQuery("SELECT r FROM Restaurant r ORDER BY Review DESC",
                Restaurant.class
        ).getResultList();

        System.out.println(
        restaurantResult.stream().map(Restaurant::toString).collect(Collectors.joining("\n")));
    }
    public static void createRestaurant(Restaurant restaurant, EntityManager em) {
        em.persist(restaurant);
    }
}
