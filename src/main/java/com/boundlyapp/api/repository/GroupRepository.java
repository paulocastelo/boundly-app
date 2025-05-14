package com.boundlyapp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boundlyapp.api.model.Group;

import java.util.UUID;

/**
 * Repositório JPA para a entidade Group.
 * Permite operações CRUD automáticas no banco de dados.
 */
public interface GroupRepository extends JpaRepository<Group, UUID> {
}
