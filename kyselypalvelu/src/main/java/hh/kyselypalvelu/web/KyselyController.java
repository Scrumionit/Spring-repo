package hh.kyselypalvelu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KyselyController {

    @GetMapping("/kyselyt")
    public String showKyselyt() {

        return "kyselyt";
    }

    @GetMapping("/uusikysely")
    public String showUusiKysely() {

        return "uusikysely";
    }
}
