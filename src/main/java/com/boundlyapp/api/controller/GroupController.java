package com.boundlyapp.api.controller;

import org.springframework.web.bind.annotation.*;

import com.boundlyapp.api.model.Group;
import com.boundlyapp.api.repository.GroupRepository;

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
