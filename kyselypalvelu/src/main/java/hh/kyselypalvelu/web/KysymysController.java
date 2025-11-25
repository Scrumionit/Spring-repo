package hh.kyselypalvelu.web;

import hh.kyselypalvelu.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import org.springframework.stereotype.Controller;
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
    public String tallennaKysymys(
            @RequestParam(required = false) Long kysely_id,
            @RequestParam(required = false, name = "vaihtoehdot") String vaihtoehdot,
            Kysymys kysymys) {

        if (kysely_id != null) {
            Kysely kysely = kyselyRepository.findById(kysely_id).orElse(null);
            if (kysely != null) {
                kysymys.setKysely(kysely);
                if (kysely.getKysymykset() == null) {
                    kysely.setKysymykset(new HashSet<>());
                }
                kysely.getKysymykset().add(kysymys);
            }
        }

        if (vaihtoehdot != null && !vaihtoehdot.trim().isEmpty()) {
            if (kysymys.getVaihtoehdot() == null) {
                kysymys.setVaihtoehdot(new HashSet<>());
            }
            Arrays.stream(vaihtoehdot.split("\\s*,\\s*"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .forEach(s -> {
                    Vaihtoehto vo = new Vaihtoehto(s);
                    vo.setKysymys(kysymys);
                    kysymys.getVaihtoehdot().add(vo);
                });
        }

        kysymysRepository.save(kysymys);

        if (kysely_id != null) {
            Kysely kysely = kyselyRepository.findById(kysely_id).orElse(null);
            if (kysely != null) {
                kyselyRepository.save(kysely);
            }
            return "redirect:/kysely/" + kysely_id;
        }

        return "redirect:/kyselyt";
    }
}
