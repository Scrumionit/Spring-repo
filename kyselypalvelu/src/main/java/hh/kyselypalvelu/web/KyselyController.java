package hh.kyselypalvelu.web;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KysymysRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(value="/tallennaKysely")
    public String tallennaKysely(Kysely kysely) {
        kyselyRepository.save(kysely);
        return "redirect:/kyselyt";
    }

    @GetMapping("/uusikysymys")
    public String naytaUusiKysymysLomake(Model model) {
        model.addAttribute("kysymys", new Kysymys());
        return "uusikysymys";
    }

    @PostMapping("/tallennaKysymys")
    public String tallennaKysymys(Kysymys kysymys) {
        kysymysRepository.save(kysymys);
        return "redirect:/kyselyt";
    }


}