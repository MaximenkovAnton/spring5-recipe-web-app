package me.spring.mvc.recipe_project.repositories;

import me.spring.mvc.recipe_project.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long>{
}
