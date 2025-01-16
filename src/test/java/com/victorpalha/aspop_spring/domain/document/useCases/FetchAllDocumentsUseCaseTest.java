package com.victorpalha.aspop_spring.domain.document.useCases;

import com.victorpalha.aspop_spring.domain.document.entity.DocumentEntity;
import com.victorpalha.aspop_spring.domain.document.repository.DocumentRepository;
import com.victorpalha.aspop_spring.domain.image.entity.ImageEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchAllDocumentsUseCaseTest {
    @InjectMocks
    private FetchAllDocumentsUseCase fetchAllDocumentsUseCase;

    @Mock
    private DocumentRepository documentRepository;

    @Test
    public void should_fetch_all_documents_if_who_request_are_member() {
        DocumentEntity document_1 = DocumentEntity
                .builder()
                .documentId("1")
                .title("Title 1")
                .isPrivate(true)
                .build();
        DocumentEntity document_2 = DocumentEntity
                .builder()
                .documentId("2")
                .title("Title 2")
                .isPrivate(false)
                .build();
        DocumentEntity document_3 = DocumentEntity
                .builder()
                .documentId("3")
                .title("Title 3")
                .isPrivate(true)
                .build();
        List<DocumentEntity> expected_documents = Arrays.asList(document_1, document_2, document_3);
        when(documentRepository.findAll()).thenReturn(expected_documents);

        List<DocumentEntity> result = fetchAllDocumentsUseCase.execute(true);

        assert expected_documents.equals(result);
        assert result.size() == expected_documents.size();
    }

    @Test
    public void should_fetch_all_public_documents_if_who_request_are_not_member() {
        DocumentEntity document_1 = DocumentEntity
                .builder()
                .documentId("1")
                .title("Title 1")
                .isPrivate(true)
                .build();
        DocumentEntity document_2 = DocumentEntity
                .builder()
                .documentId("2")
                .title("Title 2")
                .isPrivate(false)
                .build();
        DocumentEntity document_3 = DocumentEntity
                .builder()
                .documentId("3")
                .title("Title 3")
                .isPrivate(true)
                .build();
        List<DocumentEntity> expected_documents = Collections.singletonList(document_2);
        when(documentRepository.findByPrivate(false)).thenReturn(expected_documents);

        List<DocumentEntity> result = fetchAllDocumentsUseCase.execute(false);

        assert result.size() == 1;
    }

    @Test
    public void should_return_empty_response() {
        // Mock behavior
        when(documentRepository.findAll()).thenReturn(List.of());

        // Execute use case
        List<DocumentEntity> response = fetchAllDocumentsUseCase.execute(true);

        // Assertions
        assert response != null;
        assert response.isEmpty();
    }
}
