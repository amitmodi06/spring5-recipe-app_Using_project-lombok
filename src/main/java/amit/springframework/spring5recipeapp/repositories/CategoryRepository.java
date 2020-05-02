package amit.springframework.spring5recipeapp.repositories;

import amit.springframework.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * created by KUAM on 4/27/2020
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    //Creating the Spring Data JPA data query finder method
    Optional<Category> findByDescription(String description);
}
