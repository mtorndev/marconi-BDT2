/*
 * 
 */
package dao;

import entity.Persona;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PersonaDao {

    private final EntityManager em;
    String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";

    public PersonaDao() {
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public PersonaDao(EntityManager em) {
        this.em = em;
    }

    public List<Persona> findAll() {
        /* 
        https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html       
        */
        TypedQuery<Persona> typedQuery = em.createQuery("SELECT u FROM Persona u", Persona.class);
        List<Persona> personaList = typedQuery.setMaxResults(10).getResultList();
        return personaList;
    }
    
    public List<Persona> findAllQNative() {
        /*
        https://www.oracle.com/technetwork/articles/vasiliev-jpql-087123.html
        */
        List<Persona> personaList = (List<Persona>)em.createNativeQuery                      
            ("SELECT * FROM PERSONA", Persona.class)
               .setMaxResults(10)
               .getResultList();
        return personaList;
    }

    public boolean insertPersona(Persona p) {
        em.getTransaction().begin();
        try {
            em.persist(p); 
            // -- workaround cache entity manager
            em.flush();
            em.clear();   
            // --
            em.getTransaction().commit();
            
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
}
