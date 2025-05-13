package com.blondlyapp.api.repository;

import com.blondlyapp.api.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

/**
 * Repositório JPA para a entidade Group.
 * Permite operações CRUD automáticas no banco de dados.
 */
public interface GroupRepository extends JpaRepository<Group, UUID> {
}
