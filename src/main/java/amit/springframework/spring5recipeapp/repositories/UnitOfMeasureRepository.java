package amit.springframework.spring5recipeapp.repositories;

import amit.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * created by KUAM on 4/27/2020
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    //Creating the Spring Data JPA data query finder method
    Optional<UnitOfMeasure> findByDescription(String description);
}
