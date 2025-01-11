package com.victorpalha.aspop_spring.domain.image.useCases;

import com.victorpalha.aspop_spring.domain.image.entity.ImageEntity;
import com.victorpalha.aspop_spring.domain.image.exceptions.ImageNotFoundError;
import com.victorpalha.aspop_spring.domain.image.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteImageUseCaseTest {
    @InjectMocks
    private DeleteImageUseCase deleteImageUseCase;

    @Mock
    private ImageRepository imageRepository;

    @Test
    public void should_delete_image() {
        String imageId = "123123";
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setImageId(imageId);
        imageEntity.setImageUrl("https://example.com/image.png");

        when(imageRepository.findById(imageId)).thenReturn(Optional.of(imageEntity));

        ImageEntity result = deleteImageUseCase.execute(imageId);
        assert (result != null);
        assert (result.getImageUrl().equals("https://example.com/image.png"));
        assert (result.getImageId().equals(imageId));
    }

    @Test
    public void should_not_delete_image_by_id_when_not_found() {
        String imageId = "123123";

        when(imageRepository.findById(imageId)).thenReturn(Optional.empty());

        try{
            deleteImageUseCase.execute(imageId);
        } catch (Exception e) {
            assert e instanceof ImageNotFoundError;
        }

    }
}
