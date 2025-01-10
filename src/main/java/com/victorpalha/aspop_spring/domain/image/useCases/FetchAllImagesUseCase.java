package com.victorpalha.aspop_spring.domain.image.useCases;

import com.victorpalha.aspop_spring.domain.image.entity.ImageEntity;
import com.victorpalha.aspop_spring.domain.image.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Get all Images URL from the database
 * @author Victor Palha
 * @version 1.0
 * @since 10/01/25
 */
@Service
public class FetchAllImagesUseCase {
    private final ImageRepository imageRepository;
    public FetchAllImagesUseCase(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<ImageEntity> execute() {
        return imageRepository.findAll();
    }
}
