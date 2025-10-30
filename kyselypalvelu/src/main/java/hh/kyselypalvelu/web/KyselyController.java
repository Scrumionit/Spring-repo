package hh.kyselypalvelu.web;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KysymysRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class KyselyController {

    private KyselyRepository kyselyRepository;
    private KysymysRepository kysymysRepository;

    public KyselyController(KyselyRepository kyselyRepository, KysymysRepository kysymysRepository) {
        this.kyselyRepository = kyselyRepository;
        this.kysymysRepository = kysymysRepository;
    }

    @GetMapping("/kyselyt")
    public String naytaKyselyt(Model model) {
        model.addAttribute("kyselyt", kyselyRepository.findAll());
        return "kyselyt";
    }

    @GetMapping("/uusikysely")
    public String naytaUusiKyselyLomake(Model model) {
        model.addAttribute("kysely", new Kysely());
        return "uusikysely";
    }

    @PostMapping(value = "/tallennaKysely")
    public String tallennaKysely(Kysely kysely) {
        kyselyRepository.save(kysely);
        return "redirect:/kyselyt";
    }

    @GetMapping("/uusikysymys")
    public String naytaUusiKysymysLomake(Model model) {
        model.addAttribute("kysymys", new Kysymys());
        model.addAttribute("kyselyt", kyselyRepository.findAll());
        return "uusikysymys";
    }

    @PostMapping("/tallennaKysymys")
    public String tallennaKysymys(
            @RequestParam String kysymystyyppi,
            @RequestParam String kysymysteksti,
            @RequestParam Long kysely_id) {

        Kysely kysely = kyselyRepository.findById(kysely_id).orElse(null);
        if (kysely != null) {
            Kysymys kysymys = new Kysymys(kysymystyyppi, kysymysteksti, null);
            kysymys.setKysely(kysely);
            kysymysRepository.save(kysymys);
        }
        return "redirect:/kyselyt";
    }

    @GetMapping("/kysely/{id}")
    public String naytaKysely(@PathVariable("id") Long id, Model model) {
        Kysely kysely = kyselyRepository.findById(id).orElse(null);

        if (kysely == null) {
            return "redirect:/kyselyt";
        }

        model.addAttribute("kysely", kysely);
        model.addAttribute("kysymykset", kysely.getKysymykset());

        return "kysely";
    }

}