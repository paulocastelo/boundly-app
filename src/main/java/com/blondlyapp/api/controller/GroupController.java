package com.blondlyapp.api.controller;

import com.blondlyapp.api.model.Group;
import com.blondlyapp.api.repository.GroupRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para grupos.
 */
@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepository repository;

    public GroupController(GroupRepository repository) {
        this.repository = repository;
    }

    // Retorna todos os grupos
    @GetMapping
    public List<Group> getAllGroups() {
        return repository.findAll();
    }
}
