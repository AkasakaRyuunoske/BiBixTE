package BiBixTE.BiBixTE.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class AcquistiController {
    @GetMapping("/acquisti")
    public String aquistiGet(){
        return "acquisti";
    }

    @PostMapping("/acquisti")
    public String aquistiPost(){
        return "acquisti";
    }
}
