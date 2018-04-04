package me.spring.mvc.recipe_project.repositories;

import me.spring.mvc.recipe_project.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{
    Optional<UnitOfMeasure> findByUnit(String unit);
}
