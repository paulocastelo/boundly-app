package com.boundlyapp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boundlyapp.api.model.Contact;

import java.util.UUID;

/**
 * Repositório JPA para a entidade Contact.
 * Fornece métodos prontos como findAll(), findById(), save(), deleteById(), etc.
 */
public interface ContactRepository extends JpaRepository<Contact, UUID> {
}
