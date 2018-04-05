package me.spring.mvc.recipe_project.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    Recipe recipe;

    @Lob
    String recipeNote;

    public void setRecipe(Recipe recipe) {
        if(this.recipe != recipe) {
            this.recipe = recipe;
            recipe.setNote(this);
        }
    }
}
