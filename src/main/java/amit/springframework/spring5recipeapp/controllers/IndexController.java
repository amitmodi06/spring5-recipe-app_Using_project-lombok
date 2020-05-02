package amit.springframework.spring5recipeapp.controllers;

import amit.springframework.spring5recipeapp.domain.Category;
import amit.springframework.spring5recipeapp.domain.UnitOfMeasure;
import amit.springframework.spring5recipeapp.repositories.CategoryRepository;
import amit.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import amit.springframework.spring5recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * created by KUAM on 4/24/2020
 */
@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model){

        // *************** Code to show the Spring Data JPA data query finder method ******************
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        System.out.println("Cat id is: " + categoryOptional.get().getId());
        System.out.println("UOM id is: " + unitOfMeasureOptional.get().getId());
        // *********************************************************************************************

        // *************** Code to bind the recipe service with the view *******************************
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
