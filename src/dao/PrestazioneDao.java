/*
 * 
 */
package dao;

import entity.Prestazione;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PrestazioneDao {

    private final EntityManager em;
    String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";

    public PrestazioneDao() {
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public PrestazioneDao(EntityManager em) {
        this.em = em;
    }

    public List<Prestazione> findAll() {
        /* 
        https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html       
        */
        TypedQuery<Prestazione> typedQuery = em.createQuery("SELECT p FROM Prestazione p", Prestazione.class);
        List<Prestazione> prestazioneList = typedQuery.setMaxResults(10).getResultList();
        return prestazioneList;
    }
    
    public List<Prestazione> findAllQNative() {
        /*
        https://www.oracle.com/technetwork/articles/vasiliev-jpql-087123.html
        */
        List<Prestazione> prestazioneList = (List<Prestazione>)em.createNativeQuery                      
            ("SELECT * FROM PRESTAZIONE", Prestazione.class)
               .setMaxResults(10)
               .getResultList();
        return prestazioneList;
    }

    public boolean insertPrestazione(Prestazione p) {
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
