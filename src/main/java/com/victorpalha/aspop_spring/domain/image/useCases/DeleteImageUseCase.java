package com.victorpalha.aspop_spring.domain.image.useCases;

import com.victorpalha.aspop_spring.domain.image.entity.ImageEntity;
import com.victorpalha.aspop_spring.domain.image.exceptions.ImageNotFoundError;
import com.victorpalha.aspop_spring.domain.image.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This use case delete a image from database by id
 * @author Victor Palha
 * @version 1.0
 * @since 11/01/25
 */
@Service
public class DeleteImageUseCase {
    private final ImageRepository imageRepository;

    public DeleteImageUseCase(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageEntity execute(String imageId) {
        Optional<ImageEntity> imageEntity = imageRepository.findById(imageId);
        if (imageEntity.isEmpty()) {
            throw new ImageNotFoundError();
        }

        imageRepository.deleteById(imageId);
        return imageEntity.get();
    }
}
