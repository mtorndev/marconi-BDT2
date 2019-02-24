/**
 * This file was generated by the JPA Modeler
 */
package entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 * @author Marco
 */
@Entity
@IdClass(PrestazionePK.class)
public class Prestazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String Data;

    @Basic
    private String Ora;

    @Id
    @ManyToOne(targetEntity = Persona.class)
    private Persona personaRichiedente;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return this.Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getOra() {
        return this.Ora;
    }

    public void setOra(String Ora) {
        this.Ora = Ora;
    }

    public Persona getPersonaRichiedente() {
        return this.personaRichiedente;
    }

    public void setPersonaRichiedente(Persona personaRichiedente) {
        this.personaRichiedente = personaRichiedente;
    }

}