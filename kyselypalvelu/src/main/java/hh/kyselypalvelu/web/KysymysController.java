package hh.kyselypalvelu.web;

import hh.kyselypalvelu.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import java.util.Arrays;
import java.util.ArrayList;
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

    @GetMapping("/kysely/{kysely_id}/uusikysymys")
    public String uusiKysymys(@PathVariable Long kysely_id, Model model) {
        Kysymys kysymys = new Kysymys();
        kysymys.setVaihtoehdot(new ArrayList<>());
        model.addAttribute("kysymys", kysymys);
        model.addAttribute("kysymystyypit", kysymysTyyppiRepository.findAll());
        model.addAttribute("kyselyt", kyselyRepository.findAll());

        Kysely kysely = kyselyRepository.findById(kysely_id).orElse(null);
        if (kysely == null) {
            Kysely placeholder = new Kysely();
            // set id so Thymeleaf expressions using kysely.kysely_id work
            placeholder.setKysely_id(kysely_id);
            model.addAttribute("kysely", placeholder);
        } else {
            model.addAttribute("kysely", kysely);
        }
        return "uusikysymys";
    }

    @PostMapping("/kysely/{kyselyId}/tallennakysymys")
    public String tallennaKysymys(@PathVariable("kyselyId") Long kyselyId, @ModelAttribute Kysymys kysymys) {
        Kysely kysely = kyselyRepository.findById(kyselyId).orElse(null);
        if (kysely == null) {
            return "redirect:/kysely"; // tai käsittele virhe sopivasti
        }

        // linkitä kysymys kyselyyn ja varmista kysymykset-set
        kysymys.setKysely(kysely);
        if (kysely.getKysymykset() == null) {
            kysely.setKysymykset(new HashSet<>());
        }

        // Selvitä onko valittu tyyppi monivalinta (haetaan tyyppi repo:sta varmistaen nimi/ID)
        boolean onMonivalinta = false;
        if (kysymys.getKysymystyyppi() != null && kysymys.getKysymystyyppi().getKysymystyyppi_id() != null) {
            KysymysTyyppi tyyppi = kysymysTyyppiRepository.findById(kysymys.getKysymystyyppi().getKysymystyyppi_id()).orElse(null);
            if (tyyppi != null) {
                kysymys.setKysymystyyppi(tyyppi);
                if ("MONIVALINTA".equalsIgnoreCase(tyyppi.getNimi())) {
                    onMonivalinta = true;
                }
            }
        }

        // Käsittele vaihtoehdot vain jos monivalinta
        if (onMonivalinta) {
            if (kysymys.getVaihtoehdot() == null || kysymys.getVaihtoehdot().isEmpty()) {
                // no bound vaihtoehdot from the form -> keep null
                kysymys.setVaihtoehdot(null);
            } else {
                // ensure each vaihtoehto links back to kysymys
                kysymys.getVaihtoehdot().forEach(vo -> vo.setKysymys(kysymys));
            }
        } else {
            // ei monivalintaa -> poista vaihtoehdot
            kysymys.setVaihtoehdot(null);
        }

        // Tallenna kysymys ja päivitä kysely
        kysymysRepository.save(kysymys);
        kysely.getKysymykset().add(kysymys);
        kyselyRepository.save(kysely);

        return "redirect:/kysely/" + kyselyId;
    }
}
