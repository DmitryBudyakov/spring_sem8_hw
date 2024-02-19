package com.example.spring_sem8_hw_aop.repository;

import com.example.spring_sem8_hw_aop.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note, Long> {

//    Optional<Note> findById(Long id);
}
