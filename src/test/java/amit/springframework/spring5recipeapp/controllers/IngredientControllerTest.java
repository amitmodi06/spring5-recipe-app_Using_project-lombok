package amit.springframework.spring5recipeapp.controllers;

import amit.springframework.spring5recipeapp.commands.IngredientCommand;
import amit.springframework.spring5recipeapp.commands.RecipeCommand;
import amit.springframework.spring5recipeapp.services.IngredientService;
import amit.springframework.spring5recipeapp.services.RecipeService;
import amit.springframework.spring5recipeapp.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListIngredients() throws Exception{
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

        //then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void testShowIngredient() throws Exception{
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"));
    }


    @Test
    void testNewIngredientForm() throws Exception{
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        //when
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/ingredientform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("uomList"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void testUpdteIngredientForm() throws Exception{
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        //when
        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some String")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));
    }
}