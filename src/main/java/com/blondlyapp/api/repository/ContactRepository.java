package com.blondlyapp.api.repository;

import com.blondlyapp.api.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

/**
 * Repositório JPA para a entidade Contact.
 * Fornece métodos prontos como findAll(), findById(), save(), deleteById(), etc.
 */
public interface ContactRepository extends JpaRepository<Contact, UUID> {
}
