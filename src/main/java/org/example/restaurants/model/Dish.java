package org.example.restaurants.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
public class Dish {
    @Id
    private int id;
    private String name;
}
