/*
 */
package bdt;

import entity.Persona;
import entity.Prestazione;
import dao.PersonaDao;
import dao.PrestazioneDao;
import dao.DAO;

import java.util.List;
import java.util.Map;
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
        Persona persona3 = new Persona();
        persona1.setCognome("Rossi");
        persona1.setNome("Mario");
        persona2.setCognome("Verdi");
        persona2.setNome("Mauro");
        persona3.setCognome("Bianchi");
        persona3.setNome("Laura");

        personaDao.insertPersona(persona1);
        personaDao.insertPersona(persona2);
        personaDao.insertPersona(persona3);

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

        // Aggiunto associazione Fornitura Prestazione
        prestazione3.setPersonaFornitore(persona2);
        prestazione1.setPersonaFornitore(persona1);
        prestazione2.setPersonaFornitore(persona3);
        // -----------------------------------------

        prestazioneDao.insertPrestazione(prestazione1);
        prestazioneDao.insertPrestazione(prestazione2);
        prestazioneDao.insertPrestazione(prestazione3);
        // elenco delle persone     
        List<Persona> personaList = personaDao.findAllQNative();
        System.out.format("%-30s %-30s\n","Cognome","Nome");
        for (Persona pe : personaList) {
            System.out.format("%-30s %-30s\n",pe.getCognome(),pe.getNome());
        }
        
        // elenco delle prestazioni
        List<Prestazione> prestazioneList = prestazioneDao.findAll();
        System.out.println();
        System.out.format("%-10s %-10s %-30s %-30s\n","Data","Ora","Cognome Richiedente","Cognome Fornitore");
        for (Prestazione pr : prestazioneList) {
            System.out.format("%-10s %-10s %-30s %-30s\n",
                    pr.getData(),pr.getOra(),
                    pr.getPersonaRichiedente().getCognome(),
                    pr.getPersonaFornitore().getCognome());
        }
        // elenco delle persone che forniscono prestazione a persone che le ricevono  
        Map<Persona, List<Persona>> mapRF = personaDao.findAllRF();
        // Stampa della map
        System.out.println();
        System.out.format("%-30s %-30s\n","Richiedente","Fornitori");
        for (Persona rich : mapRF.keySet()) {
            String outR = "";
            String outF = "";
            outR += rich.getCognome() + ", " + rich.getNome();
            for (Persona forn : mapRF.get(rich)) {
                outF += forn.getCognome() + ", " + forn.getNome();
            }
            System.out.format("%-30s %-30s\n", outR, outF);
        }

    }
}
