package hh.kyselypalvelu.web;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KysymysRepository;

import java.util.Arrays;
import java.util.ArrayList;

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

    public KysymysController(KyselyRepository kyselyRepository, KysymysRepository kysymysRepository) {
        this.kyselyRepository = kyselyRepository;
        this.kysymysRepository = kysymysRepository;
    }

      @GetMapping("/uusikysymys")
    public String naytaUusiKysymysLomake(Model model) {
        model.addAttribute("kysymys", new Kysymys());
        model.addAttribute("kyselyt", kyselyRepository.findAll());
        return "uusikysymys";
    }
    @PostMapping("/tallennaKysymys")
    public String tallennaKysymys(@RequestParam(required = false) Long kysely_id,
            @RequestParam(required = false) String vastaus,
            Kysymys kysymys) {

        if (kysely_id != null) {
            Kysely kysely = kyselyRepository.findById(kysely_id).orElse(null);
            if (kysely != null) {
                kysymys.setKysely(kysely);
            }
        }

        // pilkotaan pilkuilla erotetut vaihtoehdot listaksi
        if (vastaus != null && !vastaus.isEmpty()) {
            kysymys.setVastaus(new ArrayList<>(Arrays.asList(vastaus.split("\\s*,\\s*"))));
        }

        kysymysRepository.save(kysymys);

        if (kysely_id != null) {
            return "redirect:/kysely/" + kysely_id;
        }

        return "redirect:/kyselyt";
    }
}
