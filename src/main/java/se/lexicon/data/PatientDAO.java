package se.lexicon.data;

import se.lexicon.model.Patient;

import java.time.LocalDate;
import java.util.List;

public interface PatientDAO extends GenericCRUD<Patient, String>{

    public List<Patient> findByBirthDateBefore(LocalDate date);
}
