package se.lexicon.data;

import se.lexicon.data.interfaces.PatientDAO;
import se.lexicon.io.JsonManager;
import se.lexicon.model.Patient;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static se.lexicon.io.URLConstants.PATIENTS_JSON;

public class PatientDAOIMPL implements PatientDAO, Serializable {


    private static PatientDAOIMPL INSTANCE;

    public static PatientDAOIMPL getInstance(){
        if (INSTANCE == null) INSTANCE = new PatientDAOIMPL(null);
        return INSTANCE;
    }

    public PatientDAOIMPL(HashSet<Patient> patientCollection) {
        if (patientCollection == null){
            this.patientCollection = new HashSet<>(JsonManager.getInstance().deserializeFromJson(new File(PATIENTS_JSON), Patient.class));
        }else{
            this.patientCollection = patientCollection;
        }
    }

    private final HashSet<Patient> patientCollection;

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
    public Optional<Patient> findById(String id) {
        return patientCollection.stream()
                .filter( patient -> patient.getId().equals(id)) //intermediate operation - Returns a Stream
                .findFirst(); //Terminal Operator - End the stream and Execute the task.
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
