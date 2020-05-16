package amit.springframework.spring5recipeapp.converters;

import amit.springframework.spring5recipeapp.commands.IngredientCommand;
import amit.springframework.spring5recipeapp.domain.Ingredient;
import amit.springframework.spring5recipeapp.domain.Recipe;
import amit.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final Long ID_VALUE = new Long(1l);
    public static final Long UOM_ID = new Long(2L);
    public static final String DESCRIPTION = "description";
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final Recipe RECIPE = new Recipe();

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setRecipe(RECIPE);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        ingredient.setUom(unitOfMeasure);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertNotNull(ingredientCommand);
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(UOM_ID, ingredientCommand.getUom().getId());

    }

    @Test
    void convertWithNullUOM(){
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setRecipe(RECIPE);
        ingredient.setUom(null);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //Then
        assertNotNull(ingredientCommand);
        assertNull(ingredientCommand.getUom());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());

    }
}