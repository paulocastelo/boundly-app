package com.blondlyapp.api.repository;

import com.blondlyapp.api.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

/**
 * Repositório JPA para a entidade Meeting.
 */
public interface MeetingRepository extends JpaRepository<Meeting, UUID> {
}
