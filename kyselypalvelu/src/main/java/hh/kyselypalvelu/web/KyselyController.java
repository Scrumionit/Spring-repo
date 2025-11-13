package hh.kyselypalvelu.web;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KysymysRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@CrossOrigin(origins = "http://localhost:5173")
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

    @PostMapping(value = "/tallennaVastaus")
    public String tallennaVastaus(@RequestParam Long kysymys_id, @RequestParam String vastaus) {
        Kysymys kysymys = kysymysRepository.findById(kysymys_id).orElse(null);
        if (kysymys != null) {
            List<String> vastauslista = kysymys.getVastaus();
            if (vastauslista == null) {
                vastauslista = new ArrayList<>();
            } else {
                // ensure mutable list
                vastauslista = new ArrayList<>(vastauslista);
            }
            vastauslista.add(vastaus);
            kysymys.setVastaus(vastauslista);
            kysymysRepository.save(kysymys);
            Long kyselyId = kysymys.getKysely().getKysely_id();
            return "redirect:/kysely/" + kyselyId;
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