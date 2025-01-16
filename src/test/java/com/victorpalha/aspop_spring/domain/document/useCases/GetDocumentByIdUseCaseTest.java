package com.victorpalha.aspop_spring.domain.document.useCases;

import com.victorpalha.aspop_spring.domain.document.entity.DocumentEntity;
import com.victorpalha.aspop_spring.domain.document.exceptions.DocumentIsPrivateOnlyToMembersError;
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
public class GetDocumentByIdUseCaseTest {
    @InjectMocks
    private GetDocumentByIdUseCase getDocumentByIdUseCase;

    @Mock
    private DocumentRepository documentRepository;

    @Test
    public void should_return_a_private_document_to_a_auth_member() {
        String documentId = "1";
        DocumentEntity expectedDocument = DocumentEntity
                .builder()
                .documentId(documentId)
                .title("title")
                .description("description")
                .isPrivate(true)
                .documentUrl("http://url")
                .build();

        when(documentRepository.findById(documentId)).thenReturn(Optional.of(expectedDocument));

        DocumentEntity result = getDocumentByIdUseCase.execute(documentId, true);
        assert result != null;
        assert result.getDocumentUrl().equals(expectedDocument.getDocumentUrl());
        assert result.getTitle().equals(expectedDocument.getTitle());
        assert result.getDescription().equals(expectedDocument.getDescription());
        assert result.isPrivate() == expectedDocument.isPrivate();
    }

    @Test
    public void should_return_a_public_document_to_a_auth_member() {
        String documentId = "1";
        DocumentEntity expectedDocument = DocumentEntity
                .builder()
                .documentId(documentId)
                .title("title")
                .description("description")
                .isPrivate(false)
                .documentUrl("http://url")
                .build();

        when(documentRepository.findById(documentId)).thenReturn(Optional.of(expectedDocument));

        DocumentEntity result = getDocumentByIdUseCase.execute(documentId, true);
        assert result != null;
        assert result.getDocumentUrl().equals(expectedDocument.getDocumentUrl());
        assert result.getTitle().equals(expectedDocument.getTitle());
        assert result.getDescription().equals(expectedDocument.getDescription());
        assert result.isPrivate() == expectedDocument.isPrivate();
    }

    @Test
    public void should_return_a_public_document_to_anyone() {
        String documentId = "1";
        DocumentEntity expectedDocument = DocumentEntity
                .builder()
                .documentId(documentId)
                .title("title")
                .description("description")
                .isPrivate(false)
                .documentUrl("http://url")
                .build();

        when(documentRepository.findById(documentId)).thenReturn(Optional.of(expectedDocument));

        DocumentEntity result = getDocumentByIdUseCase.execute(documentId, false);
        assert result != null;
        assert result.getDocumentUrl().equals(expectedDocument.getDocumentUrl());
        assert result.getTitle().equals(expectedDocument.getTitle());
        assert result.getDescription().equals(expectedDocument.getDescription());
        assert result.isPrivate() == expectedDocument.isPrivate();
    }

    @Test
    public void should_not_return_a_private_document_to_anyone() {
        String documentId = "1";
        DocumentEntity expectedDocument = DocumentEntity
                .builder()
                .documentId(documentId)
                .title("title")
                .description("description")
                .isPrivate(true)
                .documentUrl("http://url")
                .build();

        when(documentRepository.findById(documentId)).thenReturn(Optional.of(expectedDocument));

        try{
            getDocumentByIdUseCase.execute(documentId, false);
        } catch (Exception e) {
            assert e instanceof DocumentIsPrivateOnlyToMembersError;
        }
    }

    @Test
    public void should_not_return_document_if_document_does_not_exist() {
        String documentId = "1";

        when(documentRepository.findById(documentId)).thenReturn(Optional.empty());

        try{
            getDocumentByIdUseCase.execute(documentId, false);
        } catch (Exception e) {
            assert e instanceof DocumentNotFoundError;
        }
    }
}
