package com.boundlyapp.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para criação de grupos.
 */
public class GroupRequest {

    @NotBlank(message = "Group name is required")
    @Size(min = 2, max = 100)
    private String name;

    @Size(max = 255, message = "Description can have at most 255 characters")
    private String description;

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
