package com.boundlyapp.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO para criação de encontros (meetings).
 */
public class MeetingRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;
    
    @Size(max = 255, message = "Location must be at most 255 characters")
    private String location;

	@Future(message = "Date must be in the future")
    private LocalDateTime dateTime;

    @NotEmpty(message = "At least one contact must be selected")
    private List<UUID> contactsIds;

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDate(LocalDateTime date) {
        this.dateTime = date;
    }

    public List<UUID> getContactsIds() {
        return contactsIds;
    }

    public void setContactsIds(List<UUID> contactsIds) {
        this.contactsIds = contactsIds;
    }
    
    public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
