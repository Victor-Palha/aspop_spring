package com.victorpalha.aspop_spring.domain.document.useCases;

import com.victorpalha.aspop_spring.domain.document.dtos.CreateDocumentRequestDTO;
import com.victorpalha.aspop_spring.domain.document.entity.DocumentEntity;
import com.victorpalha.aspop_spring.domain.document.exceptions.DocumentNotFoundError;
import com.victorpalha.aspop_spring.domain.document.repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteDocumentUseCaseTest {
    @InjectMocks
    private DeleteDocumentUseCase deleteDocumentUseCase;

    @Mock
    private DocumentRepository documentRepository;

    @Test
    public void should_delete_document() {
        String documentId = "123123";
        CreateDocumentRequestDTO createDocumentRequestDTO = CreateDocumentRequestDTO
                .builder()
                .title("title")
                .description("description")
                .isPrivate(true)
                .documentUrl("http://localhost:8080/document/test.pdf")
                .build();
        DocumentEntity documentToBeDeleted = createDocumentRequestDTO.toEntity();
        documentToBeDeleted.setDocumentId(documentId);

        when(documentRepository.findById(documentId)).thenReturn(Optional.of(documentToBeDeleted));

        DocumentEntity result = deleteDocumentUseCase.execute(documentId);
        assert result.getDocumentId().equals(documentId);

    }

    @Test
    public void should_not_delete_document_if_document_not_found() {
        String documentId = "123123";

        when(documentRepository.findById(documentId)).thenReturn(Optional.empty());

        try{
            deleteDocumentUseCase.execute(documentId);
        }catch (Exception e) {
            assert e instanceof DocumentNotFoundError;
        }
    }
}
