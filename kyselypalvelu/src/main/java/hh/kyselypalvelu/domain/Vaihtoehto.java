package hh.kyselypalvelu.domain;

import jakarta.persistence.*;

@Entity
public class Vaihtoehto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vaihtoehto_id;

    private String teksti;

    @ManyToOne
    @JoinColumn(name = "kysymys_id", nullable = false)
    private Kysymys kysymys;

    public Vaihtoehto() {}
    public Vaihtoehto(String teksti) { this.teksti = teksti; }

    public Long getVaihtoehto_id() { return vaihtoehto_id; }
    public String getTeksti() { return teksti; }
    public void setTeksti(String teksti) { this.teksti = teksti; }

    public Kysymys getKysymys() { return kysymys; }
    public void setKysymys(Kysymys kysymys) { this.kysymys = kysymys; }
}
