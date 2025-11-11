package hh.kyselypalvelu.web;

import org.springframework.web.bind.annotation.RestController;
import hh.kyselypalvelu.domain.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


@RestController
@RequestMapping("/api")
public class KysApiController {

    private final KyselyRepository kyselyRepository;
    private final KysymysRepository kysymysRepository;

    public KysApiController(KyselyRepository kyselyRepository, KysymysRepository kysymysRepository) {
        this.kyselyRepository = kyselyRepository;
        this.kysymysRepository = kysymysRepository;
    }

    // Get all kyselyt
    @RequestMapping(value = "/kyselyt", method = RequestMethod.GET)
    public @ResponseBody List<Kysely> kyselyListRest() {
        return (List<Kysely>) kyselyRepository.findAll();
    }

    // Add a new kysely
    @PostMapping(value = "/kyselyt")
    public @ResponseBody Kysely addKyselyRest(@RequestBody Kysely kysely) {
        return kyselyRepository.save(kysely);
    }

    // Get one kysely by id
    @RequestMapping(value = "/kyselyt/{id}", method = RequestMethod.GET)
    public @ResponseBody Kysely findKyselyRest(@PathVariable("id") Long kyselyId) {
        return kyselyRepository.findById(kyselyId).orElse(null);
    }
}
