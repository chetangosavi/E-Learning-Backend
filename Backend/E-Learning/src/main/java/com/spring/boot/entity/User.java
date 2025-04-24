package com.spring.boot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Document(collection="User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class User {

    @Id
    private String id; 
    @NotBlank(message = "Name Cannot Be Null")
    private String name;
    @Email
    @NotBlank(message = "Email Cannot Be Null")
    @Indexed(unique = true)
    private String email;
    @NotBlank(message = "Password Cannot Be Null")
    private String password;
    
    private List<String> role;
    
    @DBRef
    @Builder.Default
    private List<Course> coursesPurchased = new ArrayList<>();
    
    @DBRef
    @Builder.Default
    private List<Course> coursesCreated= new ArrayList<>();
}
