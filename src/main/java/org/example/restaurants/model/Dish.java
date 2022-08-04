package org.example.restaurants.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
public class Dish {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;
    private int price;
    private String description;
    private int review;

    public Dish(String name, int price, String description, int review, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.review = review;
        this.restaurant = restaurant;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    Restaurant restaurant;


}
