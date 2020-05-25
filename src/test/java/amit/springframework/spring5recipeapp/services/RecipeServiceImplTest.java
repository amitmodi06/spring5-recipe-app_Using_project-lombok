package amit.springframework.spring5recipeapp.services;

import amit.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import amit.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import amit.springframework.spring5recipeapp.domain.Recipe;
import amit.springframework.spring5recipeapp.exceptions.NotFoundException;
import amit.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    void getRecipeById(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.getRecipeById(1L);

        assertNotNull(recipeReturned, "Null Recipe Returned");

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void getRecipeByIdNotFound(){
        assertThrows(NotFoundException.class,
                () ->{
                        Optional<Recipe> recipeOptional = Optional.empty();

                        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

                        Recipe recipeReturned = recipeService.getRecipeById(1L);
                    });
        //Should go booooom
    }

    @Test
    void getRecipes() {

        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);
        // it means when recipeRepository.findAll() is called, then return recipeData
        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById(){
        //given
        Long idToDelete = Long.valueOf(2L);
        //when
        recipeService.deleteById(idToDelete);

        //no "when", since method has void return tyoe

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}