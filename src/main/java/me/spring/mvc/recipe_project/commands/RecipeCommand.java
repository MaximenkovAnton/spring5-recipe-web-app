package me.spring.mvc.recipe_project.commands;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import me.spring.mvc.recipe_project.domain.Difficulty;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeCommand {
    Long id;
    String description;
    Integer prepTime;
    Integer cookTime;
    Integer servings;
    String source;
    String url;
    String directions;

    Set<CategoryCommand> categories = new HashSet<>();
    Set<IngredientCommand> ingredients = new HashSet<>();

    Difficulty difficulty;
    NoteCommand note;
}