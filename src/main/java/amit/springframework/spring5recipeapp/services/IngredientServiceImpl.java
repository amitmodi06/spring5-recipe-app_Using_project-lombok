package amit.springframework.spring5recipeapp.services;

import amit.springframework.spring5recipeapp.commands.IngredientCommand;
import amit.springframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import amit.springframework.spring5recipeapp.domain.Recipe;
import amit.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * created by KUAM on 5/21/2020
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if(!optionalRecipe.isPresent()){
            //todo implement error handling
            log.debug("Recipe Id not found, id: " + recipeId);
        }

        Recipe  recipe = optionalRecipe.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            //todo implement error handling
            log.debug("Ingredient Id not found, id: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }
}
