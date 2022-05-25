package BiBixTE.BiBixTE.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
@RequestMapping(path = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e, Model model) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        // Otherwise setup and send the user to a default error-view.
        model.addAttribute("exception", e);
        model.addAttribute("url", req.getRequestURL());

        log.info("Resolved: " + e);
        log.info("Resolved: " + req.getRequestURL());

        return "<h1>Errore di tipo generico</h1>" +
                "<h2>Possibili motivi sono:" +
                "<ul>L'utente non attivato</ul>" +
                "<ul>Errore di connessione</ul>" +
                "<ul>Errore parte server</ul>" +
                "<ul>Errore non previsto</ul></h2><br>" +
                "<h1>Azioni da fare:" +
                "<ul>Riprovare dopo</ul>" +
                "<ul>Contatare administratore</ul>";
    }
//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public HashMap<String, String> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
//        HashMap<String, String> response = new HashMap<>();
//        response.put("status", "fail");
//        response.put("message", e.getLocalizedMessage());
//        return response;
//    }
}
