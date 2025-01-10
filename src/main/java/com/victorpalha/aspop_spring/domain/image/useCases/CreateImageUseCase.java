package com.victorpalha.aspop_spring.domain.image.useCases;

import com.victorpalha.aspop_spring.domain.image.entity.ImageEntity;
import com.victorpalha.aspop_spring.domain.image.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.awt.*;

/**
 * This endpoint saves the image URL on the database
 * @author Victor Palha
 * @version 1.0
 * @since 09/01/25
 */
@Service
public class CreateImageUseCase {
    private final ImageRepository imageRepository;
    public CreateImageUseCase(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageEntity execute(String imageUrl){
        ImageEntity imageToSave = ImageEntity
                .builder()
                .imageUrl(imageUrl)
                .build();
        return imageRepository.save(imageToSave);
    }
}
