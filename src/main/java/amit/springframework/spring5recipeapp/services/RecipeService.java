package amit.springframework.spring5recipeapp.services;

import amit.springframework.spring5recipeapp.commands.RecipeCommand;
import amit.springframework.spring5recipeapp.domain.Recipe;

import java.util.Set;

/**
 * created by KUAM on 4/27/2020
 */
public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe getRecipeById(long l);
    RecipeCommand findCommandById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}
