package hh.kyselypalvelu.web;

import org.springframework.web.bind.annotation.RestController;
import hh.kyselypalvelu.domain.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class VastausRestController {

    private final VastausRepository vastausRepository;
    private final KysymysRepository kysymysRepository;

    public VastausRestController(VastausRepository vastausRepository, KysymysRepository kysymysRepository) {
        this.vastausRepository = vastausRepository;
        this.kysymysRepository = kysymysRepository;
    }

    // Ei testattu
    @PostMapping(value= "/vastaukset")
    public Vastaus tallennaVastausRest(@RequestBody Vastaus vastaus) {
        return vastausRepository.save(vastaus);
    }

    // Löytää kaikkien kyselyiden vastaukset – Ei testattu
    @GetMapping(value= "/vastaukset")
    public Iterable<?> naytaVastausListaRest() {
        return vastausRepository.findAll();
    }

    // Löytää tietyn kysymyksen vastaukset – Ei testattu
    @GetMapping(value = "/kyselyt/{kysely_id}/kysymykset/{kysymys_id}/vastaukset")
    public List<Vastaus> naytaKysymyksenVastauksetRest(@PathVariable("kysely_id") Long kysely_id, @PathVariable("kysymys_id") Long kysymys_id) {
        return kysymysRepository.findById(kysymys_id)
                .map(Kysymys::getVastaukset)
                .map(List::copyOf)
                .orElse(null);
    }

    // Lisää vastauksen tiettyyn kysymykseen – Ei testattu
    @PostMapping(value = "/kyselyt/{kysely_id}/kysymykset/{kysymys_id}/vastaukset")
    public Vastaus lisaaVastausKysymykseenRest(@PathVariable("kysely_id") Long kysely_id, @PathVariable("kysymys_id") Long kysymys_id, @RequestBody Vastaus vastaus) {
        Kysymys kysymys = kysymysRepository.findById(kysymys_id).orElse(null);
        if (kysymys != null) {
            vastaus.setKysymys(kysymys);
            return vastausRepository.save(vastaus);
        }
        return null;
    }

    // Löytää tietyn kyselyn vastaukset – Ei testattu
    @GetMapping(value = "/kyselyt/{kysely_id}/vastaukset")
    public List<Vastaus> naytaVastauksetForKysely(@PathVariable("kysely_id") Long kysely_id) {
        return kysymysRepository.findById(kysely_id).stream()
                .flatMap(kysymys -> kysymys.getVastaukset().stream())
                .toList();
    }
}
