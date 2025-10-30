package hh.kyselypalvelu.web;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KysymysRepository;

import java.util.Arrays;

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
            kysymys.setVastaus(Arrays.asList(vastaus.split(",")));
        }

        kysymysRepository.save(kysymys);

        if (kysely_id != null) {
            return "redirect:/kysely/" + kysely_id;
        }

        return "redirect:/kyselyt";
    }

    @GetMapping("/kysely/{kysely_id}")
    public String naytaKysely(@PathVariable Long kysely_id, Model model) {
        Kysely kysely = kyselyRepository.findById(kysely_id).orElse(null);

        if (kysely == null) {
            return "redirect:/kyselyt";
        }

        model.addAttribute("kysely", kysely);
        model.addAttribute("kysymykset", kysely.getKysymykset());

        return "kysely";
    }

}