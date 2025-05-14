package com.boundlyapp.api.controller;

import com.boundlyapp.api.dto.ContactRequest;
import com.boundlyapp.api.exception.PatchValidationException;
import com.boundlyapp.api.model.Contact;
import com.boundlyapp.api.repository.ContactRepository;
import com.boundlyapp.api.repository.GroupRepository;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Controller REST para contatos.
 * Exibe todos os contatos com GET e permite criar novo com POST.
 */
@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactRepository repository;
    private final GroupRepository groupRepository;


    // Injeção de dependência via construtor
    public ContactController(ContactRepository repository, GroupRepository groupRepository) {
        this.repository = repository;
		this.groupRepository = groupRepository;
    }

    // Retorna todos os contatos
    @GetMapping
    public List<Contact> getAllContacts() {
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable UUID id) {
    	return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cria um novo contato
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody @Valid ContactRequest request) {
        Contact contact = new Contact();
        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());

        Contact saved = repository.save(contact);

        // Retorna o contato salvo com status 201
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable UUID id,
            @RequestBody @Valid ContactRequest request
    ) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(request.getName());
                    existing.setEmail(request.getEmail());
                    existing.setPhone(request.getPhone());

                    // Verifica se foi informado um grupo
                    if (request.getGroupId() != null) {
                        groupRepository.findById(request.getGroupId())
                                .ifPresent(existing::setGroup);
                    } else {
                        existing.setGroup(null);
                    }

                    Contact updated = repository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Contact> patchContact(
            @PathVariable UUID id,
            @RequestBody Map<String, Object> updates
    ) {
        return repository.findById(id)
                .map(existing -> {
                    if (updates.containsKey("name")) {
                        existing.setName((String) updates.get("name"));
                    }

                    if (updates.containsKey("email")) {
                        existing.setEmail((String) updates.get("email"));
                    }

                    if (updates.containsKey("phone")) {
                        existing.setPhone((String) updates.get("phone"));
                    }

                    if (updates.containsKey("groupId")) {
                        Object groupIdObj = updates.get("groupId");
                        if (groupIdObj != null) {
                            try {
                                UUID groupId = UUID.fromString(groupIdObj.toString());
                                groupRepository.findById(groupId)
                                        .ifPresent(existing::setGroup);
                            } catch (IllegalArgumentException e) {
                                throw new PatchValidationException(List.of("Invalid groupId format"));
                            }
                        } else {
                            existing.setGroup(null); // remove grupo se enviado como null
                        }
                    }

                    Contact updated = repository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable UUID id) {
        return repository.findById(id)
                .map(contact -> {
                    repository.delete(contact);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
