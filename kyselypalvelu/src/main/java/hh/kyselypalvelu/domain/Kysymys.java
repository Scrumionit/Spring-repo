package hh.kyselypalvelu.domain;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Kysymys {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="kysely_id")
    private Kysely kysely;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kysymys_id;
    private String kysymystyyppi;
    private String kysymysteksti;

    @ElementCollection
    private List<String> vastaus = new ArrayList<>(); // initialize to mutable list

    public Kysymys() {
    }
    
    public Kysymys(String kysymystyyppi, String kysymysteksti, List<String> vastaus) {
        this.kysymystyyppi = kysymystyyppi;
        this.kysymysteksti = kysymysteksti;
        this.vastaus = (vastaus == null) ? new ArrayList<>() : new ArrayList<>(vastaus);
    }

    public Kysely getKysely() {
        return kysely;
    }

    public void setKysely(Kysely kysely) {
        this.kysely = kysely;
    }
    
    public Long getKysymys_id() {
        return kysymys_id;
    }

    public void setKysymys_id(Long kysymys_id) {
        this.kysymys_id = kysymys_id;
    }

    public String getKysymystyyppi() {
        return kysymystyyppi;
    }

    public void setKysymystyyppi(String kysymystyyppi) {
        this.kysymystyyppi = kysymystyyppi;
    }

    public String getKysymysteksti() {
        return kysymysteksti;
    }

    public void setKysymysteksti(String kysymysteksti) {
        this.kysymysteksti = kysymysteksti;
    }

    public List<String> getVastaus() {
        return vastaus;
    }

    public void setVastaus(List<String> vastaus) {
        this.vastaus = (vastaus == null) ? new ArrayList<>() : new ArrayList<>(vastaus);
    }

}
