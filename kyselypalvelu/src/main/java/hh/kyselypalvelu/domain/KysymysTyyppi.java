package hh.kyselypalvelu.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class KysymysTyyppi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kysymystyyppi_id;
    private String nimi;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymystyyppi")
    private List<Kysymys> kysymykset;

    public KysymysTyyppi() {
    }

    public KysymysTyyppi(String nimi) {
        this.nimi = nimi;

    }

    public Long getKysymystyyppi_id() {
        return kysymystyyppi_id;
    }

    public void setKysymystyyppi_id(Long kysymystyyppi_id) {
        this.kysymystyyppi_id = kysymystyyppi_id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public List<Kysymys> getKysymykset() {
        return kysymykset;
    }

    public void setKysymykset(List<Kysymys> kysymykset) {
        this.kysymykset = kysymykset;
    }
}