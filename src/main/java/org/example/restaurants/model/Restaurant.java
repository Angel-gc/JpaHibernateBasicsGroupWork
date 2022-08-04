package org.example.restaurants.model;

import lombok.*;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Restaurant {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String location;
    private int review;

    public Restaurant(String name, String location, int review) {
        this.name = name;
        this.location = location;
        if (review < 0 ){
            this.review = 0;
        } else this.review = Math.min(review, 5);
    }

    @OneToMany(mappedBy = "restaurant")
    List<Dish> dishes = new ArrayList<>();

}
