package amit.springframework.spring5recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * created by KUAM on 5/22/2020
 */
public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile file);
}
