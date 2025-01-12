package com.victorpalha.aspop_spring.domain.document.useCases;

import com.victorpalha.aspop_spring.domain.document.entity.DocumentEntity;
import com.victorpalha.aspop_spring.domain.document.exceptions.DocumentNotFoundError;
import com.victorpalha.aspop_spring.domain.document.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * @author Victor Palha
 * @version 1.0
 * @since 11/01/25
 */
@Service
public class GetDocumentByIdUseCase {
    private final DocumentRepository documentRepository;
    public GetDocumentByIdUseCase(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    private DocumentEntity execute(String documentId) {
        Optional<DocumentEntity> document = documentRepository.findById(documentId);
        if (document.isEmpty()) {
            throw new DocumentNotFoundError();
        }
        return document.get();
    }
}
