package com.jojen.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


/**
 * Feature List: https://projectlombok.org/features/all
 * Install IntelliJ Lombok plugin for auto-completion and general IDE support https://plugins.jetbrains.com/plugin/6317-lombok
 * Settings > Plugins > Lombok
 *
 * @Data adds getters and setters for all fields, generates toString/hashCode/equals methods,
 *          adds a required args constructor, i. e. constructor with parameters for all final fields
 * @Builder adds a builder to construct the object by chaining set-methods and calling .build()
 * @NoArgsConstructor adds a default constructor
 * @AllArgsConstructor adds a constructor with parameters for every field
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Temperature {
    @Id
    @GeneratedValue
    private Long id;
    LocalDateTime time;
    double value;
}
