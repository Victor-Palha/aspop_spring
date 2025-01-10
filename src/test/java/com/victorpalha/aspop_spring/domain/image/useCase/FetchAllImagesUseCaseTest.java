package com.victorpalha.aspop_spring.domain.image.useCase;

import com.victorpalha.aspop_spring.domain.image.entity.ImageEntity;
import com.victorpalha.aspop_spring.domain.image.repository.ImageRepository;
import com.victorpalha.aspop_spring.domain.image.useCases.FetchAllImagesUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchAllImagesUseCaseTest {
    @InjectMocks
    private FetchAllImagesUseCase fetchAllImagesUseCase;
    @Mock
    private ImageRepository imageRepository;

    @Test
    public void should_return_all_news() {
        ImageEntity image_1 = ImageEntity
                .builder()
                .imageId("1")
                .imageUrl("http://localhost:8080/images/1")
                .build();
        ImageEntity image_2 = ImageEntity
                .builder()
                .imageId("2")
                .imageUrl("http://localhost:8080/images/2")
                .build();
        List<ImageEntity> expected_images = Arrays.asList(image_1, image_2);

        when(imageRepository.findAll()).thenReturn(expected_images);

        List<ImageEntity> response = fetchAllImagesUseCase.execute();
        assert response.size() == expected_images.size();
        assert response.containsAll(expected_images);
    }

    @Test
    public void should_return_empty_response() {
        // Mock behavior
        when(imageRepository.findAll()).thenReturn(List.of());

        // Execute use case
        List<ImageEntity> response = fetchAllImagesUseCase.execute();

        // Assertions
        assert response != null;
        assert response.isEmpty();
    }
}