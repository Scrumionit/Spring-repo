package hh.kyselypalvelu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Kysely {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kysely_id;
    private String nimi;
    private String kuvaus;
    private List<Kysymys> kysymykset;

    public Kysely() {
    }

    public Kysely(String nimi, String kuvaus, List<Kysymys> kysymykset) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.kysymykset = kysymykset;
    }

    public Long getKysely_id() {
        return kysely_id;
    }
    public void setKysely_id(Long kysely_id) {
        this.kysely_id = kysely_id;
    }
    public String getNimi() {
        return nimi;
    }
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    public String getKuvaus() {
        return kuvaus;
    }
    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
    public List<Kysymys> getKysymykset() {
        return kysymykset;
    }
    public void setKysymykset(List<Kysymys> kysymykset) {
        this.kysymykset = kysymykset;
    }

    @Override
    public String toString() {
        return "Kysely [kysely_id=" + kysely_id + ", nimi=" + nimi + ", kuvaus=" + kuvaus + ", kysymykset=" + kysymykset
                + "]";
    }
    
}
