package hh.kyselypalvelu.web;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KysymysRepository;
import hh.kyselypalvelu.domain.KysymysTyyppiRepository;
import hh.kyselypalvelu.domain.Vastaus;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
public class KysymysController {

    private KyselyRepository kyselyRepository;
    private KysymysRepository kysymysRepository;
    private KysymysTyyppiRepository kysymysTyyppiRepository;

    public KysymysController(KyselyRepository kyselyRepository, KysymysRepository kysymysRepository, KysymysTyyppiRepository kysymysTyyppiRepository) {
        this.kyselyRepository = kyselyRepository;
        this.kysymysRepository = kysymysRepository;
        this.kysymysTyyppiRepository = kysymysTyyppiRepository;
    }

    @GetMapping("/uusikysymys")
    public String naytaUusiKysymysLomake(Model model) {
        model.addAttribute("kysymys", new Kysymys());
        model.addAttribute("kyselyt", kyselyRepository.findAll());
        model.addAttribute("kysymystyypit", kysymysTyyppiRepository.findAll());
        return "uusikysymys";
    }
    @PostMapping("/tallennaKysymys")
    public String tallennaKysymys(@RequestParam(required = false) Long kysely_id, @RequestParam(required = false) String vastaus, Kysymys kysymys) {

        if (kysely_id != null) {
            Kysely kysely = kyselyRepository.findById(kysely_id).orElse(null);
            if (kysely != null) {
                kysymys.setKysely(kysely);
            }
        }

        // parse comma-separated answer options into Vastaus entities
        if (vastaus != null && !vastaus.isEmpty()) {
            // ensure set exists
            if (kysymys.getVastaukset() == null) {
                kysymys.setVastaus(new HashSet<>()); // entity setter name setVastaus(...)
            }
            Arrays.asList(vastaus.split("\\s*,\\s*"))
                .stream()
                .filter(s -> !s.isEmpty())
                .forEach(s -> {
                    Vastaus v = new Vastaus(s);
                    v.setKysymys(kysymys);
                    kysymys.getVastaukset().add(v);
                });
        }

        kysymysRepository.save(kysymys);

        if (kysely_id != null) {
            return "redirect:/kysely/" + kysely_id;
        }

        return "redirect:/kyselyt";
    }
}
