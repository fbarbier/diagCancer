package fr.bordeaux.isped.sitis.diagCancer.Service;

import fr.bordeaux.isped.sitis.diagCancer.model.Patient;
import fr.bordeaux.isped.sitis.diagCancer.repository.PatientRepository;
import org.hibernate.search.engine.ProjectionConstants;
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

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> searchPatients(String searchText) {

        FullTextQuery jpaQuery = searchUsersQuery(searchText);

        List<Patient> patientList = jpaQuery.getResultList();

        return patientList;
    }

    @Override
    public List<Patient> searchPatientsFuzzy(String searchText) {

        FullTextQuery jpaQuery = searchUsersFuzzyQuery(searchText);

        List<Patient> patientList = jpaQuery.getResultList();

        return patientList;
    }

    @Override
    public List<Object[]> searchPatientsSimilar(Patient entity) {

        FullTextQuery jpaQuery = searchUsersSimilarQuery(entity);

        List<Object[]> patientList = jpaQuery.setProjection(ProjectionConstants.THIS, ProjectionConstants.SCORE).getResultList();

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

    private FullTextQuery searchUsersFuzzyQuery (String searchText) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Patient.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(1)
                .withPrefixLength(0)
                .onFields("prenom", "nom")
                .matching(searchText)
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Patient.class);

        return jpaQuery;
    }

    private FullTextQuery searchUsersSimilarQuery (Patient entity) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Patient.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .moreLikeThis()
                .comparingAllFields()
                .toEntity(entity)
                .createQuery();


        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Patient.class);

        return jpaQuery;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}


