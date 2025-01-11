package com.victorpalha.aspop_spring.domain.document.useCases;

import com.victorpalha.aspop_spring.domain.document.dtos.CreateDocumentRequestDTO;
import com.victorpalha.aspop_spring.domain.document.entity.DocumentEntity;
import com.victorpalha.aspop_spring.domain.document.repository.DocumentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateDocumentUseCase {
    private final DocumentRepository documentRepository;
    public CreateDocumentUseCase(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public DocumentEntity execute(CreateDocumentRequestDTO documentRequestDTO) {
        return documentRepository.save(documentRequestDTO.toEntity());
    }
}
