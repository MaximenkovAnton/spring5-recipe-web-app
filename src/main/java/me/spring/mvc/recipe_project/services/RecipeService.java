package me.spring.mvc.recipe_project.services;

import me.spring.mvc.recipe_project.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipies();
    Recipe findById(Long id);
}
