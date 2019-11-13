package fr.bordeaux.isped.sitis.diagCancer.repository;

import fr.bordeaux.isped.sitis.diagCancer.model.Diagnostic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
    Page<Diagnostic> findByPatientId(Long patientId, Pageable pageable);
    Optional<Diagnostic> findByIdAndPatientId(Long id, Long patientId);
}
