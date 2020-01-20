package fr.bordeaux.isped.sitis.diagCancer;

import fr.bordeaux.isped.sitis.diagCancer.model.Patient;
import fr.bordeaux.isped.sitis.diagCancer.model.Trait;
import fr.bordeaux.isped.sitis.diagCancer.repository.PatientRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class PatientTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void whenFindByName_thenReturnPatient() {
        // given
        Patient simone = new Patient(100324L, 2, new java.sql.Date(1970-12-18), "SIMONE", "RIRKIA", Trait.UNCHECKED);
        entityManager.persist(simone);
        entityManager.flush();

        // when
        Patient found = patientRepository.findByNom(simone.getNom());

        // then
        assertThat(found.getNom())
                .isEqualTo(simone.getNom());
    }

}
