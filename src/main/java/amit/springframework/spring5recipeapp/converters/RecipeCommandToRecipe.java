package amit.springframework.spring5recipeapp.converters;

import amit.springframework.spring5recipeapp.commands.RecipeCommand;
import amit.springframework.spring5recipeapp.domain.Category;
import amit.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * created by KUAM on 5/12/2020
 */
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final NotesCommandToNotes notesConverter;
    private final IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, NotesCommandToNotes notesConverter,
                                 IngredientCommandToIngredient ingredientConverter) {
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if(source == null){
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setNotes(Objects.requireNonNull(notesConverter.convert(source.getNotes())));

        if(source.getCategories() != null && source.getCategories().size()>0){
            source.getCategories()
                    .forEach( category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if(source.getIngredients() != null && source.getIngredients().size()>0){
            source.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        return recipe;
    }
}
