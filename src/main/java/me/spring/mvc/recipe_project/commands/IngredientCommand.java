package me.spring.mvc.recipe_project.commands;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientCommand {
    Long id;
    String description;
    BigDecimal amount;
    RecipeCommand recipe;
    UnitOfMeasureCommand unitOfMeasure;
}
