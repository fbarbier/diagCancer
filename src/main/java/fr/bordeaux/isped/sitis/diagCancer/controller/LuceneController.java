package fr.bordeaux.isped.sitis.diagCancer.controller;

import fr.bordeaux.isped.sitis.diagCancer.Service.PatientService;
import fr.bordeaux.isped.sitis.diagCancer.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LuceneController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/lucene")
    public List<Patient> searchPatients(){
        return patientService.searchPatients("fabien");
    }

}
