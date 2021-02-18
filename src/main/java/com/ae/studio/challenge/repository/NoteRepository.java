package com.ae.studio.challenge.repository;

import com.ae.studio.challenge.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findByUserIdOrderByCreatedAtDesc(Integer id);

}
