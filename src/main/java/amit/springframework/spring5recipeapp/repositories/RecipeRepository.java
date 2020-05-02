package amit.springframework.spring5recipeapp.repositories;

import amit.springframework.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * created by KUAM on 4/27/2020
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
