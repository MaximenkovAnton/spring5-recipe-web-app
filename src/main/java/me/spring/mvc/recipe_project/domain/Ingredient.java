package me.spring.mvc.recipe_project.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String description;
    BigDecimal amount;

    @ManyToOne
    Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    UnitOfMesure unitOfMesure;
}
