/*
 */
package bdt;

import entity.Persona;
import entity.Prestazione;
import dao.PersonaDao;
import dao.PrestazioneDao;
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
public class BDTPerPresTest {

    public static void main(String[] args) {
        // DAO
        DAO daoModel = new DAO();
        PersonaDao personaDao = daoModel.getPersonaDao();
        PrestazioneDao prestazioneDao = daoModel.getPrestazioneDao();
        
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

        prestazione1.setData("2018/11/6");
        prestazione2.setData("2018/11/7");
        prestazione3.setData("2018/12/20");
        prestazione1.setOra("12");
        prestazione2.setOra("11");
        prestazione3.setOra("10");
        prestazione1.setPersonaRichiedente(persona2);
        prestazione2.setPersonaRichiedente(persona2);
        prestazione3.setPersonaRichiedente(persona1);
        prestazioneDao.insertPrestazione(prestazione1);
        prestazioneDao.insertPrestazione(prestazione2);
        prestazioneDao.insertPrestazione(prestazione3);
             
        List<Persona> personaList = personaDao.findAllQNative();
        for (Persona pe : personaList) {
            System.out.println(pe.getCognome() + "\t" + pe.getNome());
        }
        List<Prestazione> prestazioneList = prestazioneDao.findAll();
        for (Prestazione pr : prestazioneList) {
            System.out.println(pr.getData() + "\t" + pr.getOra()+ "\t" + 
                        pr.getPersonaRichiedente().getCognome());
        }     
    }
}
