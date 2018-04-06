package me.spring.mvc.recipe_project.controllers;

import me.spring.mvc.recipe_project.domain.Recipe;
import me.spring.mvc.recipe_project.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    private IndexController indexController;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        //given
        Set<Recipe> data = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        data.add(recipe1);
        data.add(recipe2);

        when(recipeService.getRecipies()).thenReturn(data);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String indexPage = indexController.getIndexPage(model);

        //then
        assertEquals("index", indexPage);
        verify(recipeService, times(1)).getRecipies();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), data);
    }
}