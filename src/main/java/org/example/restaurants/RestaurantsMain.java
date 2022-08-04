package org.example.restaurants;

import org.example.jaystuff.model.PrimaryTeacher;
import org.example.jaystuff.model.Student;
import org.example.restaurants.db.RestaurantRepository;
import org.example.restaurants.model.Restaurant;
import org.example.restaurants.service.RepositoryEnhanced;
import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.db.Repository;
import org.example.shared.io.validation.NonBlankInputValidationRule;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantsMain {
    private static RestaurantRepository restaurantRepo = new RestaurantRepository();
    public static void main(String[] args) throws Exception {
        UserOutputService userOutputService = new ConsoleUserOutputServiceImpl();
        restaurantRepo.setProps();

        try (UserInputService userInputService = new ConsoleUserInputServiceImpl(userOutputService)) {

            while(true) {
                userOutputService.print("WELCOME");
                final String printOption = "Print all Restaurants";
                final String createOption = "Create a new Restaurant";
                final String searchNameOption = "Search by name.";
                final String searchLocationOption = "Search by location.";

                String response = userInputService.getUserChoice("Please choose an option.", printOption, createOption, searchNameOption, searchLocationOption);

                switch (response) {
                    case printOption -> restaurantRepo.printRestaurants();
                    case createOption -> {
                        String restaurantName = userInputService.getUserInput("What is the restaurants name?");
                        String restaurantLocation = userInputService.getUserInput("What is the restaurants location?");
                        int restaurantReview = Integer.parseInt(userInputService.getUserInput("What is the restaurants review? (0-5)"));
                        Restaurant restaurantToAdd = new Restaurant(restaurantName, restaurantLocation, restaurantReview);
                        restaurantRepo.save(restaurantToAdd);
                    }
                    case searchLocationOption -> {
                        System.out.println("Here is the restaurant you searched for: " + restaurantRepo.find("LOCATION", userInputService.getUserInput("What is the restaurants location?")));
                    }
                    case searchNameOption -> {
                        System.out.println("Here is the restaurant you searched for: " + restaurantRepo.find("NAME", userInputService.getUserInput("What is the restaurants name?")));
                    }
                    default -> System.out.println("");
                }
                final String endOption = "End Program";
                final String continueOption = "Continue Program";
                response = userInputService.getUserChoice("Do you want to end the program?", endOption, continueOption);
                if (response.equals(endOption)){
                    return;
                }
            }
        }
    }

//    public static void printOption(String column, String value){
//        System.out.println("Here is the restaurant you searched for: " + restaurantRepo.find(column, value));
//    }

}
