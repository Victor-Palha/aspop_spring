package com.victorpalha.aspop_spring.domain.document.useCases;

import com.victorpalha.aspop_spring.domain.document.entity.DocumentEntity;
import com.victorpalha.aspop_spring.domain.document.repository.DocumentRepository;

import java.util.List;

public class FetchAllDocumentsUseCase {
    private final DocumentRepository documentRepository;
    public FetchAllDocumentsUseCase(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<DocumentEntity> execute(boolean isAuth) {
        if (isAuth) {
            return documentRepository.findAll();
        }
        return documentRepository.findByPrivate(false);
    }
}
