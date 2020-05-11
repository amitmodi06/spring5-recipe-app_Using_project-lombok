package amit.springframework.spring5recipeapp.controllers;

import amit.springframework.spring5recipeapp.domain.Recipe;
import amit.springframework.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * created by KUAM on 5/11/2020
 */
@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService recipeService;
    @InjectMocks
    RecipeController recipeController;

    Recipe recipe;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
        recipe.setId(1L);

        mockMvc = MockMvcBuilders
                .standaloneSetup(recipeController)
                .build();
    }

    @Test
    void getRecipePage() throws Exception {

        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }
}