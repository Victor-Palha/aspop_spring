package com.victorpalha.aspop_spring.domain.document.dtos;

import com.victorpalha.aspop_spring.domain.document.entity.DocumentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDocumentRequestDTO {
    private String title;
    @URL
    private String documentUrl;
    private boolean isPrivate = false;
    private String description;

    public DocumentEntity toEntity() {
        return DocumentEntity.builder()
                .title(title)
                .documentUrl(documentUrl)
                .isPrivate(isPrivate)
                .description(description)
                .build();
    }
}
