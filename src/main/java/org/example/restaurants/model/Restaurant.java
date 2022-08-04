package org.example.restaurants.model;

import lombok.*;

import javax.annotation.processing.Generated;
import javax.persistence.*;

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
//    public Restaurant(String name, String location, String reviewString) {
//        this.name = name;
//        this.location = location;
//        int review = Integer.valueOf(reviewString);
//        if (review < 0 || review > 5){
//            this.review = 0;
//        }
//        else {
//            this.review = review;
//        }
//    }

}
