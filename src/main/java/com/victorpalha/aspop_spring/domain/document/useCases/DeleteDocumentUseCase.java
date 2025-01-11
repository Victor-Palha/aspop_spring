package com.victorpalha.aspop_spring.domain.document.useCases;

import com.victorpalha.aspop_spring.domain.document.entity.DocumentEntity;
import com.victorpalha.aspop_spring.domain.document.exceptions.DocumentNotFoundError;
import com.victorpalha.aspop_spring.domain.document.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteDocumentUseCase {
    private final DocumentRepository documentRepository;

    public DeleteDocumentUseCase(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public DocumentEntity execute(String documentId) {
        Optional<DocumentEntity> document = documentRepository.findById(documentId);
        if (document.isEmpty()) {
            throw new DocumentNotFoundError();
        }

        documentRepository.deleteById(documentId);
        return document.get();
    }
}
