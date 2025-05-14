package com.boundlyapp.api.controller;

import com.boundlyapp.api.dto.GroupRequest;
import com.boundlyapp.api.exception.PatchValidationException;
import com.boundlyapp.api.model.Group;
import com.boundlyapp.api.repository.GroupRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping
    public List<Group> getAllGroups() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody @Valid GroupRequest request) {
        Group group = new Group();
        group.setName(request.getName());
        group.setDescription(request.getDescription());

        Group saved = repository.save(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(
            @PathVariable UUID id,
            @RequestBody @Valid GroupRequest request
    ) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(request.getName());
                    existing.setDescription(request.getDescription());

                    Group updated = repository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Group> patchGroup(
            @PathVariable UUID id,
            @RequestBody Map<String, Object> updates
    ) {
        return repository.findById(id)
                .map(group -> {
                    if (updates.containsKey("name")) {
                        String name = (String) updates.get("name");
                        if (name == null || name.isBlank()) {
                            throw new PatchValidationException(List.of("Name is required"));
                        }
                        group.setName(name);
                    }

                    if (updates.containsKey("description")) {
                        group.setDescription((String) updates.get("description")); // pode ser null
                    }

                    Group updated = repository.save(group);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable UUID id) {
        return repository.findById(id)
                .map(group -> {
                    repository.delete(group);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
