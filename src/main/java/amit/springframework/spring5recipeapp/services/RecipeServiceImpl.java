package amit.springframework.spring5recipeapp.services;

import amit.springframework.spring5recipeapp.domain.Recipe;
import amit.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
    public Set<Recipe> getRecipes() {
        log.debug("I am in the RecipeServiceImpl class.");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet :: add);
        return recipeSet;
    }
}
