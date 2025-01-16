package com.victorpalha.aspop_spring.domain.document.useCases;

import com.victorpalha.aspop_spring.domain.document.dtos.CreateDocumentRequestDTO;
import com.victorpalha.aspop_spring.domain.document.entity.DocumentEntity;
import com.victorpalha.aspop_spring.domain.document.repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateDocumentUseCaseTest {
    @InjectMocks
    private CreateDocumentUseCase createDocumentUseCase;

    @Mock
    private DocumentRepository documentRepository;

    @Test
    public void should_create_document() {
        CreateDocumentRequestDTO createDocumentRequestDTO = CreateDocumentRequestDTO
                .builder()
                .title("title")
                .description("description")
                .isPrivate(true)
                .documentUrl("http://localhost:8080/document/test.pdf")
                .build();

        when(documentRepository.save(createDocumentRequestDTO.toEntity())).thenReturn(createDocumentRequestDTO.toEntity());

        DocumentEntity result = createDocumentUseCase.execute(createDocumentRequestDTO);

        assert result.getTitle().equals("title");
        assert result.getDescription().equals("description");
        assert result.getDocumentUrl().equals("http://localhost:8080/document/test.pdf");

    }
}
