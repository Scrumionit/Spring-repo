package hh.kyselypalvelu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Vastaus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vastaus_id;

    private String vastausteksti; 

    @ManyToOne // Monta vastausta voi kuulua yhteen kysymykseen
    @JoinColumn(name = "kysymys_id", nullable = false)
    private Kysymys kysymys;

    @ManyToOne // valittu vaihtoehto monivalinnassa (nullable)
    @JoinColumn(name = "vaihtoehto_id", nullable = true)
    private Vaihtoehto vaihtoehto;

    public Vastaus() {
    }

    public Vastaus(String vastausteksti) {
        this.vastausteksti = vastausteksti;
    }

    public Long getVastaus_id() {
        return vastaus_id;
    }

    public void setVastaus_id(Long vastaus_id) {
        this.vastaus_id = vastaus_id;
    }

    public String getVastausteksti() {
        return vastausteksti;
    }

    public void setVastausteksti(String vastausteksti) {
        this.vastausteksti = vastausteksti;
    }

    public Kysymys getKysymys() {
        return kysymys;
    }

    public void setKysymys(Kysymys kysymys) {
        this.kysymys = kysymys;
    }

    public Vaihtoehto getVaihtoehto() {
        return vaihtoehto;
    }

    public void setVaihtoehto(Vaihtoehto vaihtoehto) {
        this.vaihtoehto = vaihtoehto;
    }
}
