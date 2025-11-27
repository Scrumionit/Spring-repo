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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // prevent Spring from trying to bind the "vaihtoehdot" request parameter into the Kysymys.vaihtoehdot Set
        binder.setDisallowedFields("vaihtoehdot");
    }

    @GetMapping("/kysely/{kysely_id}/uusikysymys")
    public String uusiKysymys(@PathVariable Long kysely_id, Model model) {
        model.addAttribute("kysymys", new Kysymys());
        model.addAttribute("kysymystyypit", kysymysTyyppiRepository.findAll());
        model.addAttribute("kyselyt", kyselyRepository.findAll());
        if (kysely_id != null) {
            model.addAttribute("kysely", kyselyRepository.findById(kysely_id).orElse(null));
        }
        return "uusikysymys";
    }

   @PostMapping("/tallennaKysymys")
    public String tallennaKysymys(
            @RequestParam(required = false) Long kysely_id,
            @RequestParam(required = false, name = "vaihtoehdot") String vaihtoehdot,
            Kysymys kysymys) {

        // Tarkista löytyykö kysely
        if (kysely_id != null) {
            Kysely kysely = kyselyRepository.findById(kysely_id).orElse(null);
            // Yhdistä kysymys kyselyyn. Tarkista samalla, onko siellä jo kysymyksiä. Jos ei, luo uusi HashSet.
            if (kysely != null) {
                kysymys.setKysely(kysely);
                if (kysely.getKysymykset() == null) {
                    kysely.setKysymykset(new HashSet<>());
                }
                // Lisää kysymys kyselyyn, jos kysymyksiä oli jo olemassa.
                kysely.getKysymykset().add(kysymys);
            }
        }

        // Jos vaihtoehtoja ON ja ei pelkkää välilyöntiä
        if (vaihtoehdot != null && !vaihtoehdot.trim().isEmpty()) {

            // Jos ei vaihtoehtoja, luo tyhjä ArrayList.
            if (kysymys.getVaihtoehdot() == null) {
                kysymys.setVaihtoehdot(new ArrayList<>());
            }
            // Jaa split-metodilla vaihtoehdot (String, esim. "a, b, c") pilkulla listaan -> ["a", "b", "c"]
            Arrays.stream(vaihtoehdot.split("\\s*,\\s*"))
                .map(String::trim)

                // Lisää jokaisen elementin, joka ei ole tyhjä
                .filter(yksivaihtoehto -> !yksivaihtoehto.isEmpty())

                // Jokaiselle eri elementille esim. ["a", "b", "c"] tekee uuden olion vo. 
                .forEach(yksivaihtoehto -> {
                    Vaihtoehto vo = new Vaihtoehto();

                    // Asettaa yhden elementin, esim. "a" tekstiksi a, kysymykseksi kysymyksen. 
                    vo.setTeksti((String) yksivaihtoehto);
                    vo.setKysymys(kysymys);

                    // Lisää yksi elementti, esim. "a" kysymyksen vaihtoehtolistaan.
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
