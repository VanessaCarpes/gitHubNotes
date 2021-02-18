package com.ae.studio.challenge.controller;

import com.ae.studio.challenge.entity.Note;
import com.ae.studio.challenge.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepo;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Note>> getNotesByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(noteRepo.findByUserIdOrderByCreatedAtDesc(userId));
    }

    @PostMapping
    public ResponseEntity<Note> saveNote(@RequestBody Note note) {
        note.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok().body(noteRepo.save(note));
    }

    @DeleteMapping("/{note}")
    public ResponseEntity<String> deleteNote(@PathVariable Integer note) {
        noteRepo.delete(new Note(note));
        return ResponseEntity.ok().body("Note was deleted successfully!");
    }

}
