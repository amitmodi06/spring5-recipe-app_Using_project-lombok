package amit.springframework.spring5recipeapp.converters;

import amit.springframework.spring5recipeapp.commands.CategoryCommand;
import amit.springframework.spring5recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long ID_VALUE = new Long(1L);
    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void testNullObject(){
        assertNull(null);
    }

    @Test
    void testEmptyObject(){
        assertNotNull(new Category());
    }

    @Test
    void convert() {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertNotNull(categoryCommand);
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}