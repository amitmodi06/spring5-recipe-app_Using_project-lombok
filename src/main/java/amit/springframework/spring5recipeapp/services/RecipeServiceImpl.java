package amit.springframework.spring5recipeapp.services;

import amit.springframework.spring5recipeapp.commands.RecipeCommand;
import amit.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import amit.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import amit.springframework.spring5recipeapp.domain.Recipe;
import amit.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * created by KUAM on 4/27/2020
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand,
                             RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I am in the RecipeServiceImpl class.");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet :: add);
        return recipeSet;
    }

    @Override
    public Recipe getRecipeById(long l) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(l);

        if(!optionalRecipe.isPresent()){
            throw new RuntimeException("Recipe Not Found...!!!");
        }

        return optionalRecipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(getRecipeById(l));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe saveRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe: "+ saveRecipe.getId());
        return recipeToRecipeCommand.convert(saveRecipe);
    }
}
