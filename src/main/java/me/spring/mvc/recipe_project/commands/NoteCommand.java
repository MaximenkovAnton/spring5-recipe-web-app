package me.spring.mvc.recipe_project.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class NoteCommand {
    private Long id;
    private RecipeCommand recipe;
    private String recipeNote;
}
