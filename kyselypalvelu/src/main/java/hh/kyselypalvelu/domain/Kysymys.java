package hh.kyselypalvelu.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Kysymys {

    @ManyToOne
    @JoinColumn(name="kysely_id")
    private Kysely kysely;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kysymys_id;
    private String kysymystyyppi;
    private String kysymys;
    private List<String> vastaus;

    public Kysymys() {
    }
    
    public Kysymys(String kysymystyyppi, String kysymys, List<String> vastaus) {
        this.kysymystyyppi = kysymystyyppi;
        this.kysymys = kysymys;
        this.vastaus = vastaus;
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

    public String getKysymys() {
        return kysymys;
    }

    public void setKysymys(String kysymys) {
        this.kysymys = kysymys;
    }

    public List<String> getVastaus() {
        return vastaus;
    }

    public void setVastaus(List<String> vastaus) {
        this.vastaus = vastaus;
    }

    @Override
    public String toString() {
        return "Kysymys [kysymys_id=" + kysymys_id + ", kysymystyyppi=" + kysymystyyppi + ", kysymys=" + kysymys
                + ", vastaus=" + vastaus + "]";
    }
}
