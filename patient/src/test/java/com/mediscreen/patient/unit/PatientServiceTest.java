package com.mediscreen.patient.unit;

import com.mediscreen.patient.constant.Gender;
import com.mediscreen.patient.exception.RessourceNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;
    private Patient        patient = new Patient(1, "john", "doe", now(), Gender.MASCULINE, "0890009", "rue des nations");

    @Test
    void getAllPatientsTest() {
        //Arrange
        when(patientRepository.findAll()).thenReturn(List.of(patient));
        //Act
        List<Patient> patients = patientService.getAllPatients();
        //Assert
        assertThat(patients).contains(patient);
        assertThat(patients.size()).isEqualTo(1);
    }


    @Test
    void updatePatientTest_shouldReturnPatientUpdated() throws RessourceNotFoundException {
        //Arrange
        Patient patient1 = new Patient(1, "john", "doe", now(), Gender.MASCULINE, "0890009", "rue des nations 75020");
        when(patientRepository.findById(any())).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient1)).thenReturn(patient1);
        //Act
        Patient result = patientService.updatePatient(patient1);
        //Assert
        assertThat(result).isEqualToComparingFieldByField(patient1);
    }

    @Test
    void updatePatientTest_shouldThrowRessourceNotFoundException() throws RessourceNotFoundException {
        //Arrange
        Patient patient1 = new Patient(1, "john", "doe", now(), Gender.MASCULINE, "0890009", "rue des nations 75020");
        when(patientRepository.findById(any())).thenReturn(Optional.empty());

        //Act //Assert
        assertThrows(RessourceNotFoundException.class, () -> patientService.updatePatient(patient1));
    }

    @Test
    void deletePatientTest_shouldReturnDeleted() throws RessourceNotFoundException {
        //Arrange
        when(patientRepository.findById(any())).thenReturn(Optional.of(patient));
        doNothing().when(patientRepository).delete(patient);
        //Act
        Patient result = patientService.deletePatient(1);
        //Assert
        assertThat(result).isEqualToComparingFieldByField(patient);
    }

    @Test
    void deletePatientTest_shouldThrowRessourceNotFoundException() throws RessourceNotFoundException {
        //Arrange
        when(patientRepository.findById(any())).thenReturn(Optional.empty());
        //Act //Assert
        assertThrows(RessourceNotFoundException.class, () -> patientService.deletePatient(1));
    }

    @Test
    void savePatientTest_shouldReturnSaved() throws RessourceNotFoundException {
        //Arrange
        when(patientRepository.save(patient)).thenReturn(patient);
        //Act
        Patient result = patientService.savePatient(patient);
        //Assert
        assertThat(result).isEqualToComparingFieldByField(patient);
    }
}
