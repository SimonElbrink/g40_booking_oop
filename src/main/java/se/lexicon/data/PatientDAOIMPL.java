package se.lexicon.data;

import se.lexicon.model.Patient;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PatientDAOIMPL implements PatientDAO, Serializable {

//    private Patient[] patientArray;
    private HashSet<Patient> patientCollection;



    @Override
    public Patient create(Patient patient) {
        patientCollection.add(patient);
        return patient;
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(patientCollection);
    }

    @Override
    public Patient findById(String id) {
        return patientCollection.stream()
                .filter( patient -> patient.getId().equals(id)) //intermediate operation - Returns a Stream
                .findFirst() //Terminal Operator - End the stream and Execute the task.
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Patient> findByBirthDateBefore(LocalDate date) {
        return patientCollection.stream()
                .filter(patient -> patient.getBirthDate().isBefore(date) || patient.getBirthDate().isEqual(date))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(String id) {
        return patientCollection.removeIf(patient -> patient.getId().equals(id));
    }
}
