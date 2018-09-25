/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdt;

import entity.Persona;
import entity.Prestazione;
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

        Prestazione prestazione1 = new Prestazione();
        Prestazione prestazione2 = new Prestazione();
        Prestazione prestazione3 = new Prestazione();

        prestazione1.setData("2017/11/6");
        prestazione2.setData("2017/11/7");
        prestazione3.setData("2017/12/20");

        prestazione1.setOra("12");
        prestazione2.setOra("11");
        prestazione3.setOra("10");

        prestazione1.setPersonaID_R(persona1);
        prestazione2.setPersonaID_R(persona2);
        prestazione3.setPersonaID_R(persona1);

        em.persist(persona1);
        em.persist(persona2);
        em.persist(prestazione1);
        em.persist(prestazione2);
        em.persist(prestazione3);

        em.flush();
        em.clear();
        em.getTransaction().commit();
        //} else {

        TypedQuery<Persona> typedQuery;
        typedQuery = em.createQuery("SELECT DISTINCT u FROM Persona u JOIN FETCH u.prestaziones_R p", Persona.class);
        List<Persona> personaList2 = typedQuery.getResultList();
        for (Persona p : personaList2) {
            for (Prestazione pr : p.getPrestaziones_R()) {
                System.out.println(p.getCognome() + " - " + pr.getData());
            }
        }
        em.close();
    }

}
