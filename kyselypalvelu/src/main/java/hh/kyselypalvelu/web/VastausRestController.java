package hh.kyselypalvelu.web;

import hh.kyselypalvelu.domain.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class VastausRestController {

    private final VastausRepository vastausRepository;
    private final KysymysRepository kysymysRepository;
    private final KyselyRepository kyselyRepository;

    public VastausRestController(VastausRepository vastausRepository, KysymysRepository kysymysRepository,
            KyselyRepository kyselyRepository) {
        this.vastausRepository = vastausRepository;
        this.kysymysRepository = kysymysRepository;
        this.kyselyRepository = kyselyRepository;
    }

    // Tallentaa vastauksen ja lisää viitteen kysymykseen – Ei testattu
    @PostMapping(value= "/vastaukset")
    public Vastaus tallennaVastausRest(Map<String, Object> payload) {
        Object kysIdObj = payload.get("kysymys_id");
        Object tekstiObj = payload.get("vastausteksti");
        if (kysIdObj == null) {
            return null;
        }
        Long kysymysId;
        try {
            kysymysId = Long.valueOf(String.valueOf(kysIdObj));
        } catch (NumberFormatException e) {
            return null;
        }
        Kysymys kysymys = kysymysRepository.findById(kysymysId).orElse(null);
        if (kysymys == null) {
            return null;
        }
        Vastaus v = new Vastaus();
        v.setKysymys(kysymys);
        if (tekstiObj != null) v.setVastausteksti(String.valueOf(tekstiObj));
        return vastausRepository.save(v);
    }

    // Löytää kaikkien kyselyiden vastaukset – Ei testattu
    @GetMapping(value = "/vastaukset")
    public Iterable<?> naytaVastausListaRest() {
        return vastausRepository.findAll();
    }

    // Löytää tietyn kysymyksen vastaukset – Ei testattu
    @GetMapping(value = "/kyselyt/{kysely_id}/kysymykset/{kysymys_id}/vastaukset")
    public List<Vastaus> naytaKysymyksenVastauksetRest(@PathVariable("kysely_id") Long kysely_id,
            @PathVariable("kysymys_id") Long kysymys_id) {
        return kysymysRepository.findById(kysymys_id)
                .map(Kysymys::getVastaukset)
                .map(List::copyOf)
                .orElse(null);
    }

    // Lisää vastauksen tiettyyn kysymykseen – Ei testattu
    @PostMapping("/kyselyt/{kysely_id}/kysymykset/{kysymys_id}/vastaukset")
    public ResponseEntity<Vastaus> lisaaVastausKysymykseenRest(
            @PathVariable("kysely_id") Long kysely_id,
            @PathVariable("kysymys_id") Long kysymys_id,
            @RequestBody Vastaus vastaus) {

        return kysymysRepository.findById(kysymys_id)
                .map(kysymys -> {
                    // liitä kysymys entiteettiin ennen tallennusta
                    vastaus.setKysymys(kysymys);
                    Vastaus saved = vastausRepository.save(vastaus);
                    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Löytää tietyn kyselyn vastaukset – Ei testattu
    @GetMapping(value = "/kyselyt/{kysely_id}/vastaukset")
    public List<Vastaus> naytaVastauksetForKysely(@PathVariable("kysely_id") Long kysely_id) {
        return kyselyRepository.findById(kysely_id)
            .map(kysely -> kysely.getKysymykset().stream()
            .flatMap(k -> k.getVastaukset().stream())
            .toList())
            .orElse(List.of());
    }
}
