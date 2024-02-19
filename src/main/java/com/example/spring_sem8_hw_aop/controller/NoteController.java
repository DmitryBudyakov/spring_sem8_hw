package com.example.spring_sem8_hw_aop.controller;

import com.example.spring_sem8_hw_aop.aspects.TrackUserAction;
import com.example.spring_sem8_hw_aop.model.Note;
import com.example.spring_sem8_hw_aop.repository.NoteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepo noteRepo;

    /**
     * Получение всех заметок
     *
     * @return
     */
    @GetMapping
    @TrackUserAction
    public ResponseEntity<List<Note>> getAll() {
        return ResponseEntity.ok(noteRepo.findAll());
    }

    /**
     * Создание заметки
     *
     * @param note
     * @return
     */
    @PostMapping
    @TrackUserAction
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        note.setDateCreated(LocalDateTime.now());
        return ResponseEntity.ok().body(noteRepo.save(note));
    }

    /**
     * Поиск заметки по id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @TrackUserAction
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> optionalNote = noteRepo.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            return ResponseEntity.ok(note);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Обновление заметки
     *
     * @param id
     * @param updatedNote
     * @return
     */
    @PutMapping("/{id}")
    @TrackUserAction
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        Optional<Note> optionalNote = noteRepo.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setTitle(updatedNote.getTitle());
            note.setContents(updatedNote.getContents());
            return ResponseEntity.ok(noteRepo.save(note));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаление заметки по id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @TrackUserAction
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        Optional<Note> optionalNote = noteRepo.findById(id);
        if (optionalNote.isPresent()) {
            noteRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
