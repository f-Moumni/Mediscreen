package com.mediscreen.note.service;

import com.mediscreen.note.dto.PatientDto;
import com.mediscreen.note.exception.RessourceNotFoundException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.proxy.PatientProxy;
import com.mediscreen.note.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class NoteServiceTest {
    private PatientDto patient = new PatientDto(1, "john", "doe", now(), "MASCULINE", "0890009", "rue des nations");
    Note note = new Note("62d12c25191bcc3f11d08547", 1, LocalDate.now(), "a few notes for a test");
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private PatientProxy patientProxy;
    @InjectMocks
    private NoteService    noteService;

    @Test
    public void saveNoteTest_shouldReturnNoteAdded() throws RessourceNotFoundException {
        //Arrange
        when(noteRepository.insert(note)).thenReturn(note);
        when(patientProxy.getPatientById(1)).thenReturn(Optional.of(patient));
        //Act
        Note result = noteService.saveNote(note);
        //Assert
        assertThat(result).isEqualToComparingFieldByField(note);

    }

    @Test
    public void saveNoteTest_shouldThrowException()  {
        //Arrange
        when(noteRepository.insert(note)).thenReturn(note);
        when(patientProxy.getPatientById(1)).thenReturn(Optional.empty());
        //Assert
        assertThrows(RessourceNotFoundException.class, () -> noteService.saveNote(note));

    }

    @Test
    public void updateNoteTest_shouldReturnNoteUpdate() throws RessourceNotFoundException {
        //Arrange
        when(noteRepository.findById(any())).thenReturn(Optional.of(note));
        when(noteRepository.save(note)).thenReturn(note);
        when(patientProxy.getPatientById(1)).thenReturn(Optional.of(patient));
        //Act
        Note result = noteService.updateNote(note);
        //Assert
        assertThat(result).isEqualToComparingFieldByField(note);

    }

    @Test
    public void updateNoteTest_shouldThrowRessourceNotFoundException() throws RessourceNotFoundException {
        //Arrange
        when(noteRepository.findById(any())).thenReturn(Optional.empty());

        //Act//Assert
        assertThrows(RessourceNotFoundException.class, () -> noteService.updateNote(note));

    }
    @Test
    public void updateNoteTest_ForUnRegistrePatient_shouldThrowRessourceNotFoundException() throws RessourceNotFoundException {
        //Arrange
        when(noteRepository.findById(any())).thenReturn(Optional.of(note));
        when(patientProxy.getPatientById(1)).thenReturn(Optional.empty());
        //Act//Assert
        assertThrows(RessourceNotFoundException.class, () -> noteService.updateNote(note));

    }

    @Test
    public void deleteNoteTest_shouldReturnNoteDeleted() throws RessourceNotFoundException {
        //Arrange
        when(noteRepository.findById(any())).thenReturn(Optional.of(note));
        doNothing().when(noteRepository).delete(note);
        //Act
        Note result = noteService.deleteNote(note.getId());
        //Assert
        assertThat(result).isEqualToComparingFieldByField(note);

    }

    @Test
    public void deleteNoteTest_shouldThrowRessourceNotFoundException() throws RessourceNotFoundException {
        //Arrange
        when(noteRepository.findById(any())).thenReturn(Optional.empty());
        //Act//Assert
        assertThrows(RessourceNotFoundException.class, () -> noteService.deleteNote(note.getId()));

    }

    @Test
    public void findAllByPatientIdTest_shouldReturn_NotesForGivenPatientId() throws RessourceNotFoundException {
        //Arrange
        when(noteRepository.findByPatientId(2)).thenReturn(List.of(note));
        //Act
        List<Note> result = noteService.findAllByPatientId(2);
        //Assert
        assertThat(result).contains(note);
        assertThat(result.size()).isEqualTo(1);
    }

}
