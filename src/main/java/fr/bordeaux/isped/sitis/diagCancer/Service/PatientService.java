package fr.bordeaux.isped.sitis.diagCancer.Service;

import fr.bordeaux.isped.sitis.diagCancer.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> searchPatients(String searchText);

}
