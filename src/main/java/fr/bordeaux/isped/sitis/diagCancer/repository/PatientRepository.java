package fr.bordeaux.isped.sitis.diagCancer.repository;

import fr.bordeaux.isped.sitis.diagCancer.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
