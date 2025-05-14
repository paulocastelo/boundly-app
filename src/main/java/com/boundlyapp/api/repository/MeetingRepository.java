package com.boundlyapp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boundlyapp.api.model.Meeting;

import java.util.UUID;

/**
 * Repositório JPA para a entidade Meeting.
 */
public interface MeetingRepository extends JpaRepository<Meeting, UUID> {
}
