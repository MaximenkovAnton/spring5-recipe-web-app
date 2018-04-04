package me.spring.mvc.recipe_project.services;

import me.spring.mvc.recipe_project.domain.Recipe;
import me.spring.mvc.recipe_project.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    @Override
    public Set<Recipe> getRecipies() {
        Set<Recipe> recipes= new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
}
