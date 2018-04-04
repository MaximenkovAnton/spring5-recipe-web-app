package me.spring.mvc.recipe_project.bootstrap;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.spring.mvc.recipe_project.domain.*;
import me.spring.mvc.recipe_project.repositories.CategoryRepository;
import me.spring.mvc.recipe_project.repositories.RecipeRepository;
import me.spring.mvc.recipe_project.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{
    final CategoryRepository categoryRepository;
    final RecipeRepository recipeRepository;
    final UnitOfMeasureRepository unitOfMeasureRepository;

    List<Recipe> recipes;

    UnitOfMeasure eachUnit;
    UnitOfMeasure teaspoonUnit;
    UnitOfMeasure tablespoonUnit;
    UnitOfMeasure dashUnit;
    UnitOfMeasure pintUnit;
    UnitOfMeasure cupUnit;

    Category mexicanCategory;
    Category americanCategory;

    private List<Recipe> getRecipes(){
        initRecipeList();
        initUnitsOfMeasure();
        initCategories();
        createNewRecipes();
        return recipes;
    }

    private void createNewRecipes() {
        createGuac();
        createTaco();
    }

    private void createTaco() {
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Buffalo Chicken Taco");
        tacoRecipe.setPrepTime(10);
        tacoRecipe.setCookTime(10);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setDirections(
                "Place chicken in a bowl and season with chili powder, cumin, and garlic powder. Spoon chicken" +
                        "into taco shells. Pour Buffalo wing sauce over seasoned chicken and top with blue cheese."
        );
        Note tacoNote = new Note();
        tacoNote.setRecipeNote("Soft tacos can replace the taco shells, if desired.");
        tacoNote.setRecipe(tacoRecipe);
        tacoRecipe.setNote(tacoNote);

        Set<Ingredient> ingredients = tacoRecipe.getIngredients();
        ingredients.add(new Ingredient("Grilled chicken", new BigDecimal(4), eachUnit));
        ingredients.add(new Ingredient("Chili powder", new BigDecimal(0.5), teaspoonUnit));
        ingredients.add(new Ingredient("Ground cumin", new BigDecimal(0.5), teaspoonUnit));
        ingredients.add(new Ingredient("Garlic powder", new BigDecimal(0.5), teaspoonUnit));
        ingredients.add(new Ingredient("white corn taco shells", new BigDecimal(3), dashUnit));
        ingredients.add(new Ingredient("Hot Buffalo wing sauce", new BigDecimal(0.25), cupUnit));
        ingredients.add(new Ingredient("Crumbled blue cheese", new BigDecimal(0.25), cupUnit));
        connectIngredientsWithRecipe(tacoRecipe, ingredients);
        tacoRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacoRecipe);

    }

    private void connectIngredientsWithRecipe(Recipe tacoRecipe, Set<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(tacoRecipe);
        }
    }

    private void createGuac() {
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Yummy Guacamole");
        guacRecipe.setPrepTime(30);
        guacRecipe.setCookTime(30);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections(
                "1. Mash avocados with a fork in a large bowl. Mix tomatoes, cilantro, green onions, jalapeno" +
                        "peppers, garlic, and lime juice into mashed avocados; season with salt and black pepper.\n" +
                "2. Place avocado seeds in guacamole. Cover bowl tightly with plastic wrap; refrigerate for 30 minutes."
        );
        Note guacNote = new Note();
        guacNote.setRecipeNote("My husband begs me to make this all the time and my 2-year-old can't get enough of" +
                "it! It never lasts for more than a day in our house. If you like guacamole, you will LOVE this" +
                "recipe. My sister and I have been tweaking it for years and finally have come up with the best" +
                "recipe ever. The key is to use avocados that are perfectly ripe."
        );
        guacNote.setRecipe(guacRecipe);
        guacRecipe.setNote(guacNote);

        Set<Ingredient> ingredients = guacRecipe.getIngredients();
        ingredients.add(new Ingredient("Hass avocados", new BigDecimal(8), pintUnit));
        ingredients.add(new Ingredient("Tomatoes", new BigDecimal(3), eachUnit));
        ingredients.add(new Ingredient("Chopped fresh cilantro", new BigDecimal(0.5), cupUnit));
        ingredients.add(new Ingredient("Green onions", new BigDecimal(4), eachUnit));
        ingredients.add(new Ingredient("Jalapeno peppers", new BigDecimal(4), eachUnit));
        ingredients.add(new Ingredient("Garlic", new BigDecimal(4), eachUnit));
        ingredients.add(new Ingredient("Limes", new BigDecimal(3), eachUnit));
        ingredients.add(new Ingredient("Salt", new BigDecimal(0.5), tablespoonUnit));
        connectIngredientsWithRecipe(guacRecipe,ingredients);
        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);
    }

    private void initCategories() {
        mexicanCategory = getCategoryOrException("Mexican");
        americanCategory = getCategoryOrException("American");
    }

    private void initUnitsOfMeasure() {
        eachUnit = getUnitOfMeasureOrException("Each");
        teaspoonUnit = getUnitOfMeasureOrException("Teaspoon");
        tablespoonUnit = getUnitOfMeasureOrException("Tablespoon");
        dashUnit = getUnitOfMeasureOrException("Dash");
        pintUnit = getUnitOfMeasureOrException("Pint");
        cupUnit = getUnitOfMeasureOrException("Cup");
    }

    private void initRecipeList() {
        recipes = new ArrayList<>(2);
    }

    private UnitOfMeasure getUnitOfMeasureOrException(String unit){
        Optional<UnitOfMeasure> u = unitOfMeasureRepository.findByUnit(unit);
        if(!u.isPresent()) throw new RuntimeException("Expected UoM not found");
        return u.get();
    }
    private Category getCategoryOrException(String category){
        Optional<Category> c = categoryRepository.findByDescription(category);
        if(!c.isPresent()) throw new RuntimeException("Expected Category not found");
        return c.get();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }
}
