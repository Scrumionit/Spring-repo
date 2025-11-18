package hh.kyselypalvelu.web;

import org.springframework.web.bind.annotation.RestController;
import hh.kyselypalvelu.domain.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class KysymysRestController {

    private final KysymysRepository kysymysRepository;

    public KysymysRestController(KysymysRepository kysymysRepository) {
        this.kysymysRepository = kysymysRepository;
    }

    // Ei testattu
    @PostMapping(value= "/kysymykset")
    public Kysymys tallennaKysymysRest(@RequestBody Kysymys kysymys) {
        return kysymysRepository.save(kysymys);
    }

    // Löytää kaikkien kyselyiden kysymykset, lisättävää viite kyselyyn mihin kysymys kuuluu.
    @GetMapping(value= "/kysymykset")
    public List<Kysymys> naytaKysymysListaRest() {
        return (List<Kysymys>) kysymysRepository.findAll();
    }

    /*
    @GetMapping(value = "/kyselyt/{id}/kysymykset")
    public @ResponseBody List<Kysymys> naytaKyselynKysymyksetRest(@PathVariable("id") Long id) {
        return kyselyRepository.findById(id)
                .map(Kysely::getKysymykset)
                .orElse(Collections.emptyList());
    }
    @PostMapping(value = "/kyselyt/{id}/kysymykset")
    public @ResponseBody Kysymys addKysymysToKyselyRest(@PathVariable("id") Long kyselyId, @RequestBody Kysymys kysymys) {
        Kysely kysely = kyselyRepository.findById(kyselyId).orElse(null);
        if (kysely != null) {
            kysymys.setKysely(kysely);
            return kysymysRepository.save(kysymys);
        } else {
            return null;
        }
    }*/
}

