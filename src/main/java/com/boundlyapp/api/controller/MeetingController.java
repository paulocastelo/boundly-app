package com.boundlyapp.api.controller;

import com.boundlyapp.api.dto.MeetingRequest;
import com.boundlyapp.api.exception.PatchValidationException;
import com.boundlyapp.api.model.Contact;
import com.boundlyapp.api.model.Meeting;
import com.boundlyapp.api.repository.ContactRepository;
import com.boundlyapp.api.repository.MeetingRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;
import java.util.UUID;

/**
 * Controller REST para encontros (meetings).
 */
@RestController
@RequestMapping("/meetings")
public class MeetingController {

    private final MeetingRepository meetingRepository;
    private final ContactRepository contactRepository;

    public MeetingController(MeetingRepository meetingRepository, ContactRepository contactRepository) {
        this.meetingRepository = meetingRepository;
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable UUID id) {
        return meetingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Meeting> createMeeting(@RequestBody @Valid MeetingRequest request) {
        // Buscar contatos participantes pelos UUIDs
        List<Contact> participants = contactRepository.findAllById(request.getContactsIds());

        // Criar nova instância de Meeting
        Meeting meeting = new Meeting();
        meeting.setTitle(request.getTitle());
        meeting.setDescription(request.getDescription());
        meeting.setLocation(request.getLocation());
        meeting.setDateTime(request.getDate());
        meeting.setParticipants(participants);

        // Salvar
        Meeting saved = meetingRepository.save(meeting);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Meeting> updateMeeting(
            @PathVariable UUID id,
            @RequestBody @Valid MeetingRequest request
    ) {
        return meetingRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(
                        (request.getTitle() == null || request.getTitle().isBlank())
                            ? "Untitled Meeting"
                            : request.getTitle()
                    );
                    existing.setDescription(request.getDescription());
                    existing.setLocation(request.getLocation());
                    existing.setDateTime(request.getDate());

                    // Atualizar participantes
                    List<Contact> participants = contactRepository.findAllById(request.getContactsIds());
                    existing.setParticipants(participants);

                    Meeting updated = meetingRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Meeting> patchMeeting(
            @PathVariable UUID id,
            @RequestBody Map<String, Object> updates
    ) {
        return meetingRepository.findById(id)
            .map(meeting -> {
                if (updates.containsKey("title")) {
                    String title = (String) updates.get("title");
                    meeting.setTitle((title == null || title.isBlank()) ? "Untitled Meeting" : title);
                }

                if (updates.containsKey("description")) {
                    meeting.setDescription((String) updates.get("description"));
                }

                if (updates.containsKey("location")) {
                    meeting.setLocation((String) updates.get("location"));
                }

                if (updates.containsKey("date")) {
                    try {
                        String dateStr = (String) updates.get("date");
                        LocalDateTime date = LocalDateTime.parse(dateStr);
                        meeting.setDateTime(date); // <- importante
                    } catch (Exception e) {
                        throw new PatchValidationException(List.of("Invalid date format. Use yyyy-MM-ddTHH:mm:ss"));
                    }
                }

                if (updates.containsKey("contactsIds")) {
                    try {
                        List<String> ids = (List<String>) updates.get("contactsIds");
                        List<UUID> uuids = ids.stream().map(UUID::fromString).toList();
                        List<Contact> participants = contactRepository.findAllById(uuids);
                        meeting.setParticipants(participants);
                    } catch (Exception e) {
                        throw new PatchValidationException(List.of("One or more contact IDs are invalid"));
                    }
                }

                Meeting updated = meetingRepository.save(meeting);
                return ResponseEntity.ok(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable UUID id) {
        return meetingRepository.findById(id)
                .map(meeting -> {
                    meetingRepository.delete(meeting);
                    return ResponseEntity.noContent().<Void>build(); // 204
                })
                .orElse(ResponseEntity.notFound().build()); // 404
    }

}
