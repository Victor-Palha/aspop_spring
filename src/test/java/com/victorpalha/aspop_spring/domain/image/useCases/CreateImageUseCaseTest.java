package com.victorpalha.aspop_spring.domain.image.useCases;

import com.victorpalha.aspop_spring.domain.image.entity.ImageEntity;
import com.victorpalha.aspop_spring.domain.image.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateImageUseCaseTest {
    @InjectMocks
    private CreateImageUseCase createImageUseCase;
    @Mock
    private ImageRepository imageRepository;

    @Test
    public void should_create_a_new_image() {
        String imageUrl = "http://localhost:8080/image/test.png";
        ImageEntity imageEntity = ImageEntity
                .builder()
                .imageId("123123")
                .imageUrl(imageUrl)
                .build();
        when(imageRepository.save(any(ImageEntity.class))).thenReturn(imageEntity);
        ImageEntity response = createImageUseCase.execute(imageUrl);
        assert response != null;
        assert response.getImageUrl().equals(imageUrl);
        assert response.getImageId().equals(imageEntity.getImageId());
    }
}
