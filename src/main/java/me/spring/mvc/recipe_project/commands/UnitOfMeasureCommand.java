package me.spring.mvc.recipe_project.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UnitOfMeasureCommand {
    private Long id;
    private String unit;
}
