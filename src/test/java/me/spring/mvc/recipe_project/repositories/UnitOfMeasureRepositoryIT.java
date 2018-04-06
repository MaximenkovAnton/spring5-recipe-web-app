package me.spring.mvc.recipe_project.repositories;

import me.spring.mvc.recipe_project.domain.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository repository;

    @Test
    public void findByUnit() {
        Optional<UnitOfMeasure> optionalUnit = repository.findByUnit("Teaspoon");

        assertEquals(optionalUnit.get().getUnit(), "Teaspoon");
    }
}