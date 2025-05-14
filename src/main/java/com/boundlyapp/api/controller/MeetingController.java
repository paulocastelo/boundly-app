package com.boundlyapp.api.controller;

import org.springframework.web.bind.annotation.*;

import com.boundlyapp.api.model.Meeting;
import com.boundlyapp.api.repository.MeetingRepository;

import java.util.List;

/**
 * Controller REST para meetings (encontros).
 */
@RestController
@RequestMapping("/meetings")
public class MeetingController {

    private final MeetingRepository repository;

    public MeetingController(MeetingRepository repository) {
        this.repository = repository;
    }

    // Retorna todos os encontros
    @GetMapping
    public List<Meeting> getAllMeetings() {
        return repository.findAll();
    }
}
