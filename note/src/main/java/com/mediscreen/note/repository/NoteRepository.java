package com.mediscreen.note.repository;

import com.mediscreen.note.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * note repository interface
 */
@Repository
public interface NoteRepository extends MongoRepository<Note,String>{

    public List<Note> findByPatientId(Integer id);
}
