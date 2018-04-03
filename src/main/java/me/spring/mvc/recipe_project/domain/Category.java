package me.spring.mvc.recipe_project.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String description;

    @ManyToMany(mappedBy = "categories")
    Set<Recipe> recipes;
}
