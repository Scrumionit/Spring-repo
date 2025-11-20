package hh.kyselypalvelu.domain;

import java.util.Set;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Kysymys {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "kysely_id", nullable = false)
    private Kysely kysely;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kysymys_id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "kysymystyyppi_id", nullable = false)
    private KysymysTyyppi kysymystyyppi;
    private String kysymysteksti;

    @OneToMany(mappedBy = "kysymys", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Vaihtoehto> vaihtoehdot = new HashSet<>();

    @OneToMany(mappedBy = "kysymys", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Vastaus> vastaukset = new HashSet<>();

    public Kysymys() {
    }

    public Kysymys(KysymysTyyppi kysymystyyppi, String kysymysteksti) {
        this.kysymystyyppi = kysymystyyppi;
        this.kysymysteksti = kysymysteksti;
        this.vastaukset = new HashSet<>();
    }

    public Kysymys(KysymysTyyppi kysymystyyppi, String kysymysteksti, Set<Vastaus> vastaukset) {
        this.kysymystyyppi = kysymystyyppi;
        this.kysymysteksti = kysymysteksti;
        this.vastaukset = (vastaukset != null) ? vastaukset : new HashSet<>();
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

    public KysymysTyyppi getKysymystyyppi() {
        return kysymystyyppi;
    }

    public void setKysymystyyppi(KysymysTyyppi kysymystyyppi) {
        this.kysymystyyppi = kysymystyyppi;
    }

    public String getKysymysteksti() {
        return kysymysteksti;
    }

    public void setKysymysteksti(String kysymysteksti) {
        this.kysymysteksti = kysymysteksti;
    }

    public Set<Vastaus> getVastaukset() {
        return vastaukset;
    }

    public void setVastaus(Set<Vastaus> vastaukset) {
        this.vastaukset = vastaukset;
    }

    public Set<Vaihtoehto> getVaihtoehdot() { 
        return vaihtoehdot; 
    }

    public void setVaihtoehdot(Set<Vaihtoehto> vaihtoehdot) {
        this.vaihtoehdot = vaihtoehdot;
    }
}
