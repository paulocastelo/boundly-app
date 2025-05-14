package com.boundlyapp.api.controller;

import org.springframework.web.bind.annotation.*;

import com.boundlyapp.api.model.Contact;
import com.boundlyapp.api.repository.ContactRepository;

import java.util.List;

/**
 * Controller REST para contatos.
 * Exibe todos os contatos com método GET em /contacts.
 */
@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactRepository repository;

    // Injeção de dependência do repositório via construtor
    public ContactController(ContactRepository repository) {
        this.repository = repository;
    }

    // Retorna todos os contatos
    @GetMapping
    public List<Contact> getAllContacts() {
        return repository.findAll();
    }
}
