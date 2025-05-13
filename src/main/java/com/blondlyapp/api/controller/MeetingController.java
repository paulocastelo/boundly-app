package com.blondlyapp.api.controller;

import com.blondlyapp.api.model.Meeting;
import com.blondlyapp.api.repository.MeetingRepository;
import org.springframework.web.bind.annotation.*;

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
