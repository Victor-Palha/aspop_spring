package com.victorpalha.aspop_spring.domain.image.useCases;

import com.victorpalha.aspop_spring.domain.image.entity.ImageEntity;
import com.victorpalha.aspop_spring.domain.image.exceptions.ImageNotFoundError;
import com.victorpalha.aspop_spring.domain.image.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Get a unique image by ID
 * @author Victor Palha
 * @version 1.0
 * @since 11/01/25
 */
@Service
public class GetImageByIdUseCase {
    private final ImageRepository imageRepository;
    public GetImageByIdUseCase(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    public ImageEntity execute(String imageId) {
        Optional<ImageEntity> imageEntity = imageRepository.findById(imageId);
        if (imageEntity.isEmpty()) {
            throw new ImageNotFoundError();
        }
        return imageEntity.get();
    }
}
