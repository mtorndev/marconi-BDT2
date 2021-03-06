//
// This file was generated by the Jeddict
//
package entity;

import java.io.Serializable;

public class PrestazionePK implements Serializable {

    private Long id;

    private Long personaRichiedente;

    private Long personaFornitore;

    public PrestazionePK() {
    }

    public PrestazionePK(Long id, Long personaRichiedente, Long personaFornitore) {
        this.id = id;
        this.personaRichiedente = personaRichiedente;
        this.personaFornitore = personaFornitore;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonaRichiedente() {
        return this.personaRichiedente;
    }

    public void setPersonaRichiedente(Long personaRichiedente) {
        this.personaRichiedente = personaRichiedente;
    }

    public Long getPersonaFornitore() {
        return this.personaFornitore;
    }

    public void setPersonaFornitore(Long personaFornitore) {
        this.personaFornitore = personaFornitore;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!java.util.Objects.equals(getClass(), obj.getClass())) {
            return false;
        }
        final PrestazionePK other = (PrestazionePK) obj;
        if (!java.util.Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        if (!java.util.Objects.equals(this.getPersonaRichiedente(), other.getPersonaRichiedente())) {
            return false;
        }
        if (!java.util.Objects.equals(this.getPersonaFornitore(), other.getPersonaFornitore())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        hash = 97 * hash + (this.getPersonaRichiedente() != null ? this.getPersonaRichiedente().hashCode() : 0);
        hash = 97 * hash + (this.getPersonaFornitore() != null ? this.getPersonaFornitore().hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "PrestazionePK{" + " id=" + id + ", personaRichiedente=" + personaRichiedente + ", personaFornitore=" + personaFornitore + '}';
    }

}