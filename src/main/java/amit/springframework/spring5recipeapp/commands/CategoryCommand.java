package amit.springframework.spring5recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * created by KUAM on 5/11/2020
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
    private Set<RecipeCommand> recipes;
}
