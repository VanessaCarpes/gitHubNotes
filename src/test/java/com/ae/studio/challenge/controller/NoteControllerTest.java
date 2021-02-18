package com.ae.studio.challenge.controller;

import com.ae.studio.challenge.ChallengeApplication;
import com.ae.studio.challenge.entity.Note;
import com.ae.studio.challenge.repository.NoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = ChallengeApplication.class)
class NoteControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private NoteRepository noteRepository;

    @Test
    void getNotesByUser() throws Exception {
        ObjectMapper json = new ObjectMapper();
        List<Note> notesMock = Arrays.asList(new Note(1), new Note(2));

        when(noteRepository.findByUserIdOrderByCreatedAtDesc(any(Integer.class))).thenReturn(notesMock);

        mvc.perform(get("/api/notes/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(json.writeValueAsString(notesMock)));
    }

    @Test
    void saveNote() throws Exception {
        Note newNote = new Note(1);
        ObjectMapper json = new ObjectMapper();

        when(noteRepository.save(newNote)).thenReturn(newNote);

        mvc.perform(post("/api/notes")
                .contentType("application/json")
                .content(json.writeValueAsString(newNote)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteNote() throws Exception {
        Note newNote = new Note(1);
        doNothing().when(noteRepository).delete(newNote);

        mvc.perform(delete("/api/notes/1")).andExpect(status().isOk());
    }
}