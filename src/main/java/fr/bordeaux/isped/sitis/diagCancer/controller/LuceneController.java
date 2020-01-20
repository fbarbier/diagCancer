package fr.bordeaux.isped.sitis.diagCancer.controller;

import fr.bordeaux.isped.sitis.diagCancer.Service.PatientService;
import fr.bordeaux.isped.sitis.diagCancer.model.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class LuceneController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/lucene")
    public List<Patient> searchPatients(){
        return patientService.searchPatients("fabien");
    }

    @GetMapping("/lucenefuzzy")
    public List<Patient> searchPatientsFuzzy(){
        return patientService.searchPatientsFuzzy("JEANNE POARTY");
    }

    @GetMapping("/lucenesimilar")
    public List<Patient> searchPatientsSimilar(){

        List <Patient> patients = patientService.findAll();

        //Patient simone = new Patient(100324L, 2, new java.sql.Date(1970-12-18), "SIMONE", "RIRKIA");
        //patients = Arrays.asList(new Patient(100324L, 2, new java.sql.Date(1970-12-18), "SIMONE", "RIRKIA"),
        //                         new Patient(103069L, 2, new java.sql.Date(1954-03-01), "JEANNE", "POARTY"));

        List<Patient> results = new LinkedList<Patient>();

        patients.forEach(item->{

        for (Object[] resultWithScore : patientService.searchPatientsSimilar(item)) {
            if ((Float) resultWithScore[1] > 7.01 && (Float) resultWithScore[1] < 8.01) {
                results.add((Patient) resultWithScore[0]);
            }
        }
        });

        return results;


    }

}
