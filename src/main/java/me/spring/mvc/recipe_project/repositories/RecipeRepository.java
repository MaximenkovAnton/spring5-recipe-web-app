package me.spring.mvc.recipe_project.repositories;

import me.spring.mvc.recipe_project.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
}
