/*
 */
package bdt;

import entity.Persona;
import dao.PersonaDao;
import dao.DAO;
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
    
   
    public static void main(String[] args) {
        
        //PersonaDao personaDao = new PersonaDao();
        DAO daoModel = new DAO();
        PersonaDao personaDao = daoModel.getPersonaDao();
        
        Persona persona1 = new Persona();
        Persona persona2 = new Persona();
        persona1.setCognome("Rossi");
        persona1.setNome("Mario");
        persona2.setCognome("Verdi");
        persona2.setNome("Mauro");
        
        personaDao.insertPersona(persona1);
        personaDao.insertPersona(persona2);
        
        
        List<Persona> personaList = personaDao.findAll();
        for (Persona p : personaList) {
            System.out.println(p.getCognome() + " - " + p.getNome());
        }
    }
}
