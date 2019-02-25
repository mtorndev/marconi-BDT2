/*
 * 
 */
package dao;

import entity.Persona;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.Tuple;

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
        List<Persona> personaList = (List<Persona>) em.createNativeQuery("SELECT * FROM PERSONA", Persona.class)
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

    // Jpql - tutte le tuple ( personaRichiedente , personaFornitore )
    public List<Tuple> findAllTupleRF() {
        /* 
        https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html       
         */
        List<Tuple> lo = em.createQuery(
                "SELECT r,f FROM Persona r JOIN Prestazione p ON r=p.personaRichiedente JOIN Persona f ON f=p.personaFornitore"
        ).setMaxResults(10).getResultList();
        return lo;
    }

    public Map<Persona, List<Persona>> findAllRF() {
        // si costruisce la Map che ad ogni Persona R associa la lista
        // delle persone che hanno fornito Prestazioni
        // lo => lista di tutte le coppie estratte dalla doppia JOIN
        List<Tuple> lo = findAllTupleRF();
        // si definisce la map
        Map<Persona, List<Persona>> mapRF = new HashMap<>();
        // Con la stessa lista si crea una map richeidente e suoi fornitori
        for (Object pr : lo) {
            Object[] prArray = (Object[]) pr;
            Persona R = (Persona) (prArray[0]);
            Persona F = (Persona) (prArray[1]);
            if (!mapRF.containsKey(R)) // crea Key e lista Vuota
            {
                mapRF.put(R, new ArrayList<Persona>());
            }
            // inserisce nella chiave R(ichiedente) un altro l'elemento F(ornitore)
            mapRF.get(R).add(F);
        }
        return mapRF;
    }
}
//
//        for (Object pr : lo) {
//            Object[] prArray = (Object[]) pr;
//            Persona R = (Persona) (prArray[0]);
//            Persona F = (Persona) (prArray[1]);
//
//            System.out.println(" " + R.getId() + " " + F.getId() + "\n");
//        };
