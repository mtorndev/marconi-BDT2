/*
 */
package bdt;

import entity.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Marco
 */
public class BDTTest {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    private static final String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";
    private static EntityManagerFactory factory;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        //if (Integer.parseInt(JOptionPane.showInputDialog("0=Lettura / 1=Aggiungi")) == 1) {
        em.getTransaction().begin();

        Persona persona1 = new Persona();
        Persona persona2 = new Persona();

        persona1.setCognome("Rossi");
        persona1.setNome("Mario");
        persona2.setCognome("Verdi");
        persona2.setNome("Mauro");

        em.persist(persona1);
        em.persist(persona2);
 
        em.flush();
        em.clear();
        em.getTransaction().commit();
        //} else {

        TypedQuery<Persona> typedQuery;
        typedQuery = em.createQuery("SELECT u FROM Persona u", Persona.class);
        List<Persona> personaList2 = typedQuery.getResultList();
        for (Persona p : personaList2) {
            System.out.println(p.getCognome() + " - " + p.getNome());
        }
        em.close();
    }
    
}
