package fr.bordeaux.isped.sitis.diagCancer.Service;

import fr.bordeaux.isped.sitis.diagCancer.model.Patient;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PatientService patientService;

    @Override
    public List<Patient> searchPatients(String searchText) {

        FullTextQuery jpaQuery = searchUsersQuery(searchText);

        List<Patient> patientList = jpaQuery.getResultList();

        return patientList;
    }

    private FullTextQuery searchUsersQuery (String searchText) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Patient.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .wildcard()
                .onFields("prenom", "nom")
                .matching(searchText + "*")
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Patient.class);

        return jpaQuery;
    }
}
