package hh.kyselypalvelu.web;

import org.springframework.web.bind.annotation.RestController;
import hh.kyselypalvelu.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class KysymysRestController {
    private final KysymysRepository kysymysRepository;
    private final KyselyRepository kyselyRepository;

    public KysymysRestController(KysymysRepository kysymysRepository, KyselyRepository kyselyRepository) {
        this.kysymysRepository = kysymysRepository;
        this.kyselyRepository = kyselyRepository;
    }

    // Ei testattu
    @PostMapping(value= "/kysymykset")
    public Kysymys tallennaKysymysRest(@RequestBody Kysymys kysymys) {
        return kysymysRepository.save(kysymys);
    }

    // Löytää kaikkien kyselyiden kysymykset
    @GetMapping(value= "/kysymykset")
    public List<Kysymys> naytaKysymysListaRest() {
        return (List<Kysymys>) kysymysRepository.findAll();
    }

    // Löytää tietyn kyselyn kysymykset
    @GetMapping(value = "/kyselyt/{id}/kysymykset")
    public Set<Kysymys> naytaKyselynKysymyksetRest(@PathVariable("id") Long id) {
        return kyselyRepository.findById(id)
            .map(Kysely::getKysymykset)
            .orElse(null);
    }

    // Lisää kysymyksen tiettyyn kyselyyn
    @PostMapping(value = "/kyselyt/{id}/kysymykset")
    public Kysymys lisaaKysymysKyselyynRest(@PathVariable("id") Long kyselyId, @RequestBody Kysymys kysymys) {
        Kysely kysely = kyselyRepository.findById(kyselyId).orElse(null);
        if (kysely != null) {
            kysymys.setKysely(kysely);
            return kysymysRepository.save(kysymys);
        } else {
            return null;
        }
    }
}

