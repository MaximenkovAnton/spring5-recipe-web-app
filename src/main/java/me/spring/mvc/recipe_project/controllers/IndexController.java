package me.spring.mvc.recipe_project.controllers;

import me.spring.mvc.recipe_project.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private final RecipeService recipeService;

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipeService.getRecipies());
        return "index";
    }

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}
