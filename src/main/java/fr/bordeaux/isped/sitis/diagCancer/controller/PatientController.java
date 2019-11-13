package fr.bordeaux.isped.sitis.diagCancer.controller;

import fr.bordeaux.isped.sitis.diagCancer.exception.ResourceNotFoundException;
import fr.bordeaux.isped.sitis.diagCancer.model.Patient;
import fr.bordeaux.isped.sitis.diagCancer.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @PostMapping("/patients")
    public Patient createPatient(@Valid @RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @PutMapping("/patients/{patientId}")
    public Patient updatePatient(@PathVariable Long patientId, @Valid @RequestBody Patient patientRequest) {
        return patientRepository.findById(patientId).map(post -> {
            post.setSexe(patientRequest.getSexe());
            post.setDateNaissance(patientRequest.getDateNaissance());
            post.setPrenom(patientRequest.getPrenom());
            post.setNom(patientRequest.getNom());
            return patientRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PatientId " + patientId + " not found"));
    }

    @DeleteMapping("/patients/{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable Long patientId) {
        return patientRepository.findById(patientId).map(patient -> {
            patientRepository.delete(patient);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PatientId " + patientId + " not found"));
    }
}
