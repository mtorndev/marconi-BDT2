/*
 */
package bdt;

import entity.Persona;
import dao.PrestazioneDao;
import dao.DAO;
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
public class BDTPPTest {

    public static void main(String[] args) {

        //PersonaDao personaDao = new PrestazioneDao();
        DAO daoModel = new DAO();
        PrestazioneDao personaDao = daoModel.getPersonaDao();
        // Creazione Persone
        Persona persona1 = new Persona();
        Persona persona2 = new Persona();
        persona1.setCognome("Rossi");
        persona1.setNome("Mario");
        persona2.setCognome("Verdi");
        persona2.setNome("Mauro");

        personaDao.insertPersona(persona1);
        personaDao.insertPersona(persona2);
        // Creazione Prestazioni
        Prestazione prestazione1 = new Prestazione();
        Prestazione prestazione2 = new Prestazione();
        Prestazione prestazione3 = new Prestazione();

        prestazione1.setData("2017/11/6");
        prestazione2.setData("2017/11/7");
        prestazione2.setData("2017/12/20");
        prestazione1.setOra("12");
        prestazione2.setOra("11");
        prestazione2.setOra("10");

        List<Persona> personaList = personaDao.findAll();
        for (Persona p : personaList) {
            System.out.println(p.getCognome() + " - " + p.getNome());
        }
    }
}
