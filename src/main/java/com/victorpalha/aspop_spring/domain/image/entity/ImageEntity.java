package com.victorpalha.aspop_spring.domain.image.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "images")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {
    @Id
    private String imageId;
    @URL
    private String imageUrl;
}
