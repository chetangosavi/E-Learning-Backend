package com.spring.boot.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    private String id;
    
    @NotBlank(message = "Title cannot be empty")
    
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private List<Video> videos;
    private LocalDateTime date;

    @Builder.Default
    private boolean approved = false;
}

