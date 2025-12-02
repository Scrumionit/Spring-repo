package hh.kyselypalvelu.web;

import org.springframework.web.bind.annotation.RestController;
import hh.kyselypalvelu.domain.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class KyselyRestController {
    private final KyselyRepository kyselyRepository;

    public KyselyRestController(KyselyRepository kyselyRepository) {
        this.kyselyRepository = kyselyRepository;
    }

    // TOIMII HYVIN. Palauttaa kaikki kyselyt.
    @GetMapping(value = "/kyselyt")
    public List<Kysely> naytaKyselytRest() {
        return (List<Kysely>) kyselyRepository.findAll();
    }

    // Ei testattu
    @PostMapping(value= "/kyselyt")
    public Kysely tallennaKyselyRest(@RequestBody Kysely kysely) {
        return kyselyRepository.save(kysely);
    }

    // Toimii oikein. Hakee yhden kyselyn ID:llä.
    @GetMapping(value= "/kyselyt/{id}")
    public Kysely naytaKyselyRest(@PathVariable("id") Long kysely_id) {
        return kyselyRepository.findById(kysely_id).orElse(null);
    }
}
