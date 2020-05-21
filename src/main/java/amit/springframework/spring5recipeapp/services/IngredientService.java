package amit.springframework.spring5recipeapp.services;

import amit.springframework.spring5recipeapp.commands.IngredientCommand;

/**
 * created by KUAM on 5/21/2020
 */
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
