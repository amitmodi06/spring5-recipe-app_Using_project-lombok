package amit.springframework.spring5recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * created by KUAM on 5/22/2020
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {

    }
}
