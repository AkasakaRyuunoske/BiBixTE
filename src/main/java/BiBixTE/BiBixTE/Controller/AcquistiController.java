package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Repository.AcquistiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class AcquistiController {
    @Autowired
    AcquistiRepository acquistiRepository;

    @GetMapping("/acquista/monster")
    public String aquistiGET(Model model){
        log.info("Prodotto: monster");
        return "acquisti";
    }

    @PostMapping("/acquisti")
    public String aquistiPOST(){
        return "acquisti";
    }
}
