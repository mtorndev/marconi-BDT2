/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
/**
 *
 * @author Marco
 */
public class DAO {
    String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";
    private final EntityManager em;
    private PersonaDao personaDao;
    
    public DAO (){
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
        personaDao = new PersonaDao(em);
    }

    public PersonaDao getPersonaDao() {
        return personaDao;
    }
    
}
